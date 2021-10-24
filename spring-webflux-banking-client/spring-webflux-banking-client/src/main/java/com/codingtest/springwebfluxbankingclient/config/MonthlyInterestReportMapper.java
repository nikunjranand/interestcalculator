package com.codingtest.springwebfluxbankingclient.config;

import com.codingtest.springwebfluxbankingclient.model.MonthlyInterestReport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MonthlyInterestReportMapper implements RowMapper<MonthlyInterestReport>  {

    public MonthlyInterestReport mapRow(ResultSet rs, int rowNum) throws SQLException {
        MonthlyInterestReport monthlyInterestReport = new MonthlyInterestReport();
        monthlyInterestReport.setBankStateBranchCode(rs.getString("bankStateBranchCode"));
        monthlyInterestReport.setIdentification(rs.getString("identification"));
        monthlyInterestReport.setInterest(rs.getFloat("interest"));
        return monthlyInterestReport;
    }
}
