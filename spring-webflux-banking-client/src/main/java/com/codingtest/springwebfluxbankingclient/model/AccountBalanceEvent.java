package com.codingtest.springwebfluxbankingclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountBalanceEvent {

    private AccountBalance accountBalance;
    private LocalDateTime eventCreationDate;
    
}
