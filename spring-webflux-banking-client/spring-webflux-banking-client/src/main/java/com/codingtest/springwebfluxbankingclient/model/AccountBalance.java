package com.codingtest.springwebfluxbankingclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountBalance {
    Date balanceDate;
    List<Account> accounts;

}
