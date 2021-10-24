package com.codingtest.springwebfluxbankingclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MonthlyInterestReport {
    private String bankStateBranchCode;
    private String identification;
    private String month;
    private Float interest;
}
