CREATE TABLE wallet_passbook
(
  id numeric(10,0) NOT NULL PRIMARY KEY,
  user_id numeric(10,0) NOT NULL,
  second_user_id numeric(10,0) NOT NULL,
  txn_type numeric(10,0) NOT NULL,
  status boolean NOT NULL,
  tot_tx_amt double precision,
  created_at date NOT NULL,
  tot_acc_amt double precision,
  txn_id character varying(100)
);

CREATE TABLE transaction_details
(
  id numeric(10,0) NOT NULL PRIMARY KEY,
  wallet_id numeric(10,0) NOT NULL,
  txn_amt double precision,
  txn_charge double precision,
  txn_comsn double precision
)

CREATE SEQUENCE seq_wallet INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 10 CACHE 1;
CREATE SEQUENCE seq_txn INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 10 CACHE 1;

