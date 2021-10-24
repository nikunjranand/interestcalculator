package com.codingtest.springwebfluxbankingclient.config;

import com.codingtest.springwebfluxbankingclient.entity.AccountEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<AccountEntity> {

    public AccountEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBankStateBranchCode(rs.getString("bankStateBranchCode"));
        accountEntity.setIdentification(rs.getString("identification"));
        accountEntity.setOpeningDate(rs.getDate("openingDate"));
        accountEntity.setBalance(rs.getDouble("balance"));
        accountEntity.setBalanceDate(rs.getDate("balanceDate"));
        accountEntity.setInterest(rs.getFloat("interest"));
        return accountEntity;
    }
}
