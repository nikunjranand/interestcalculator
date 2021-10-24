package com.codingtest.springwebfluxbankingclient.repository;

import com.codingtest.springwebfluxbankingclient.config.AccountMapper;
import com.codingtest.springwebfluxbankingclient.config.MonthlyInterestReportMapper;
import com.codingtest.springwebfluxbankingclient.entity.AccountEntity;
import com.codingtest.springwebfluxbankingclient.model.MonthlyInterestReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {

    @Autowired
    JdbcTemplate template;

    /* Adding into account details to database table */
    public int addAccount(AccountEntity accountEntity) {
        String query = "INSERT INTO accounts (bankStateBranchCode, identification, openingDate , balance , balanceDate , interest ) VALUES(?,?,?,?,?,?)";
        return template.update(query, accountEntity.getBankStateBranchCode() , accountEntity.getIdentification() , accountEntity.getOpeningDate() , accountEntity.getBalance() , accountEntity.getBalanceDate() , accountEntity.getInterest());
    }

    /* Getting all accounts details */
    public List<AccountEntity> getAllAccounts() {

        String SQL = "select * from accounts";
        List <AccountEntity> accountEntities = template.query(SQL, new AccountMapper());
        return accountEntities;

    }

    /* Get  account details based on date */
    public List<AccountEntity> getDailyAccountDetails(String balanceDate) {
        String SQL = "select * from accounts where balanceDate = '" + balanceDate + "'";
        List <AccountEntity> accountEntities = template.query(SQL, new AccountMapper());
        return accountEntities;
    }

    /* Update account interest */
    public void updateAccountInterest(AccountEntity accountEntity) {
        String sqlQuery = "update accounts set " +
                "interest = ? " +
                "where bankStateBranchCode = ? and identification = ? and balanceDate = ?";
        template.update(sqlQuery
                , accountEntity.getInterest()
                , accountEntity.getBankStateBranchCode()
                , accountEntity.getIdentification()
                , accountEntity.getBalanceDate());
    }

    /* Getting month wise interest details */
    public List<MonthlyInterestReport> getMonthWiseAccountDetails(String startDate , String endDate) {
        String SQL = "select bankStateBranchCode , identification, sum (interest) as interest from accounts where balanceDate between '"+ startDate + "' and '" + endDate + "' group by bankStateBranchCode,identification";
        List <MonthlyInterestReport> monthlyInterestReportList = template.query(SQL, new MonthlyInterestReportMapper());
        return monthlyInterestReportList;
    }

}
