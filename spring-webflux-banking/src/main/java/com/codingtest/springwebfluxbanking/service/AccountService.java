package com.codingtest.springwebfluxbanking.service;

import com.codingtest.springwebfluxbanking.model.Account;
import com.codingtest.springwebfluxbanking.model.AccountBalance;
import com.codingtest.springwebfluxbanking.model.AccountBalanceEvent;
import com.codingtest.springwebfluxbanking.model.AccountCreateEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public int createAccountCounter = 0;

    public Mono<AccountCreateEvent> accountCreateStream() throws ParseException {
        Account account = this.generateDataForCreateAccount();
        return Mono.just(new AccountCreateEvent(account, LocalDateTime.now()));

    }

    public Mono<AccountBalanceEvent> accountBalanceStream() throws ParseException{
        AccountBalanceEvent accountBalanceEvent = this.generateDataForAccountBalance();
        return Mono.just(accountBalanceEvent);

    }

    public Account generateDataForCreateAccount() throws ParseException {
        Account account = new Account();
        List<Account> accounts = new ArrayList<>();
        Account account1= new Account("182182","111222333",formatter.parse("2021-09-13"),new Double(0.00));
        Account account2 = new Account("182182","222000111",formatter.parse("2021-09-14") , new Double(12.34));
        Account account3 = new Account("182182","222000999",formatter.parse("2021-09-15") , new Double(0.00));

        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);


        account = accounts.get(createAccountCounter);

        //this counter is used to generate three different account from sample data
        createAccountCounter = createAccountCounter + 1;
        if(createAccountCounter == 3) {
            createAccountCounter = 0;
        }

        return account;
    }

    public AccountBalanceEvent generateDataForAccountBalance() throws ParseException {
        List<Account> accounts = new ArrayList<>();
        Account account1 = new Account("182182","111222333",formatter.parse("2021-09-13"),new Double(123.34));
        Account account2 = new Account("182182","222000111",formatter.parse("2021-09-13") , new Double(12.34));
        Account account3 = new Account("182182","222000999",formatter.parse("2021-09-13") , new Double(0.00));

        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);

        AccountBalance accountBalance = new AccountBalance(formatter.parse("2021-09-19") , accounts);

        return new AccountBalanceEvent(accountBalance , LocalDateTime.now() );
    }



}
