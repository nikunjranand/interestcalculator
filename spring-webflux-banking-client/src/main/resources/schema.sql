DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts
(
  bankStateBranchCode VARCHAR,
  identification VARCHAR,
  openingDate  DATE,
  balance DOUBLE DEFAULT 0,
  balanceDate DATE DEFAULT null,
  interest FLOAT DEFAULT 0
);


