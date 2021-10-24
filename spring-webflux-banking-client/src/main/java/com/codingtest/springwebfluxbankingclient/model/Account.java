package com.codingtest.springwebfluxbankingclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class Account {

    private String bankStateBranchCode;
    private String identification;
    private Date openingDate;
    private Double balance;
}
