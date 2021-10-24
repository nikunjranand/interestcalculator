package com.codingtest.springwebfluxbankingclient.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class AccountEntity {

    private String bankStateBranchCode;
    private String identification;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date openingDate;
    private Double balance;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date balanceDate;
    private Float interest;

}
