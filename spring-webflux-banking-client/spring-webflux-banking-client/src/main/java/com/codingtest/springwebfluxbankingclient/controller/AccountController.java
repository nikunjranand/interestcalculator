package com.codingtest.springwebfluxbankingclient.controller;

import com.codingtest.springwebfluxbankingclient.entity.AccountEntity;
import com.codingtest.springwebfluxbankingclient.model.MonthlyInterestReport;
import com.codingtest.springwebfluxbankingclient.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController
{
    @Autowired
    private AccountService accountService;

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    @PostMapping (value = "/create", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void createAccount() {
        logger.info("AccountController : createAccount started");
        this.accountService.createAccount();
        logger.info("AccountController : createAccount ended");
    }

    @PutMapping (value = "/balance", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void balance() {
        logger.info("AccountController : balance started");
        this.accountService.getBalance();
        logger.info("AccountController : balance ended");
    }

    @GetMapping (value = "/getAllAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AccountEntity> getAllAccounts() {
        logger.info("AccountController : getAllAccounts started");
        List<AccountEntity> accountEntityList = this.accountService.getAllAccounts();
        logger.info("AccountController : getAllAccounts ended");
        return  accountEntityList;
    }

    @PutMapping (value = "/calculateDailyInterest", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateDailyInterest() throws ParseException {
        logger.info("AccountController : updateDailyInterestForAccount started");
        this.accountService.updateDailyInterest();
        logger.info("AccountController : updateDailyInterestForAccount ended");
    }

    @GetMapping (value = "/generateMonthlyInterestReport", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<MonthlyInterestReport> getMonthlyReportInterest() throws ParseException {
        logger.info("AccountController : getMonthlyReportInterest started");
        List<MonthlyInterestReport> monthlyInterestReportList = this.accountService.getMonthlyInterestReport();
        logger.info("AccountController : getMonthlyReportInterest ended");
        return monthlyInterestReportList;
    }
}
