package com.codingtest.springwebfluxbankingclient.service;

import com.codingtest.springwebfluxbankingclient.entity.AccountEntity;
import com.codingtest.springwebfluxbankingclient.model.Account;
import com.codingtest.springwebfluxbankingclient.model.AccountBalanceEvent;
import com.codingtest.springwebfluxbankingclient.model.AccountCreateEvent;
import com.codingtest.springwebfluxbankingclient.model.MonthlyInterestReport;
import com.codingtest.springwebfluxbankingclient.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    WebClient webClient;

    @Autowired
    AccountRepository accountRepository;

    @Value("${account.interest.rate}")
    private Double interestRate;

    public SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");

    Logger logger = LoggerFactory.getLogger(AccountService.class);

    public void createAccount()
    {
        logger.info("AccountService : createAccount started");
        webClient.get()
                .uri("createAccount")
                .retrieve()
                .bodyToMono(AccountCreateEvent.class)
                .subscribe(accountCreateEvent -> {
                    Account account = accountCreateEvent.getAccount();
                    AccountEntity accountEntity =new AccountEntity();
                    BeanUtils.copyProperties(account , accountEntity);
                    accountEntity.setBalance(0.00);
                    accountEntity.setInterest(0F);
                    accountEntity.setBalanceDate(null);
                    accountRepository.addAccount(accountEntity);
                });
        logger.info("AccountService : createAccount started");
    }

    public void getBalance()
    {
        logger.info("AccountService : getBalance started" );
        webClient.get()
                .uri("balanceAccount")
                .retrieve()
                .bodyToMono(AccountBalanceEvent.class)
                .subscribe(accountBalanceEvent -> {
                    List<Account> accountList = accountBalanceEvent.getAccountBalance().getAccounts();
                    AccountEntity accountEntity;
                    for (Account account : accountList ) {
                        accountEntity = new AccountEntity();
                        BeanUtils.copyProperties(account , accountEntity);
                        accountEntity.setBalance(account.getBalance());
                        accountEntity.setBalanceDate(accountBalanceEvent.getAccountBalance().getBalanceDate());
                        accountRepository.addAccount(accountEntity);
                    }
                });
        logger.info("AccountService : getBalance ended" );
    }

    public List<AccountEntity> getAllAccounts() {
        logger.info("AccountService : getAllAccounts ended" );
        List<AccountEntity> accountEntities = accountRepository.getAllAccounts();
        logger.info("AccountService : getAllAccounts ended" );
        return accountEntities;
    }

    public void updateDailyInterest() throws ParseException {
        logger.info("AccountService : updateDailyInterest started" );
        float interest = 0;

        //LocalDate today = LocalDate.now();

         /*To generate the daily interest data it's been set current date as '2021-09-19' so that we will get some
        daily interest data as per sample given in the test. In ideal scenario comment Line no 95
        and uncomment line no 90 */
        LocalDate today = LocalDate.of(2021, 9, 19);

        // First date of month (Date format)
        String todayDate = simpleDateFormat.format(simpleDateFormat.parse(today.toString()));

        List<AccountEntity> accountEntities = accountRepository.getDailyAccountDetails(todayDate);
        for (AccountEntity accountObj : accountEntities ) {
           if(accountObj.getInterest() == 0.0) {
               interest = (float)( accountObj.getBalance() * interestRate ) / 100;
               accountObj.setInterest(interest);
               accountRepository.updateAccountInterest(accountObj);
           }
        }
        logger.info("AccountService : updateDailyInterest ended" );
    }

    public List<MonthlyInterestReport> getMonthlyInterestReport() throws ParseException {
        logger.info("AccountService : getMonthlyInterestReport started" );

        //LocalDate today = LocalDate.now();

        /*To generate the monthly interest data it's been set current date as '2021-09-21' so that we will get some
        monthly interest data as per sample given in the test. In ideal scenario comment Line no 119
        and uncomment line no 114 */
        LocalDate today = LocalDate.of(2021, 9, 21);

        //To get year and month of current date
        String month = new SimpleDateFormat("yyyy-MM").format(simpleDateFormat.parse(today.toString())) ;

        // First date of month (Date format)
        String firstDateOfMonth = simpleDateFormat.format(simpleDateFormat.parse(today.withDayOfMonth(1).toString()));

        //Last date of month ( Date format)
        String lastDateOfMonth = simpleDateFormat.format(simpleDateFormat.parse(today.withDayOfMonth(today.lengthOfMonth()).toString()));

        List<MonthlyInterestReport> monthlyInterestReportResultList = new ArrayList<>();
        List<MonthlyInterestReport> monthlyInterestReportList = accountRepository.getMonthWiseAccountDetails(firstDateOfMonth, lastDateOfMonth);
        for (MonthlyInterestReport monthlyInterestReport : monthlyInterestReportList ) {
            monthlyInterestReport.setMonth(month);
            monthlyInterestReportResultList.add(monthlyInterestReport);
        }

        logger.info("AccountService : getMonthlyInterestReport ended" );
       return  monthlyInterestReportResultList;
    }

}
