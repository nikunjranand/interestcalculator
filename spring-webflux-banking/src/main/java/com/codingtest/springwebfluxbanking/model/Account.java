package com.codingtest.springwebfluxbanking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    String bankStateBranchCode;
    String identification;
    Date openingDate;
    Double balance;
}
