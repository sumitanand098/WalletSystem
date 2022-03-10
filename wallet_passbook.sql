CREATE TABLE wallet_users
(
  id numeric(10,0) NOT NULL PRIMARY KEY,
  first_name character varying(100),
  last_name character varying(100),
  user_name character varying(100),
  user_pwd character varying(100),
  email character varying(256),
  created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
  is_active numeric(1,0) DEFAULT 1
);

CREATE TABLE wallet_txn_passbook
(
  id numeric(10,0) NOT NULL PRIMARY KEY,
  dr_wallet_id numeric(10,0) NOT NULL,
  cr_wallet_id numeric(10,0) NOT NULL,
  txn_type numeric(10,0) NOT NULL,
  txn_status numeric(10,0) NOT NULL,
  tot_tx_amt double precision,
  created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
  tot_acc_amt double precision,
  txn_id character varying(100)
);

CREATE TABLE txn_details
(
  id numeric(10,0) NOT NULL PRIMARY KEY,
  dr_wallet_id numeric(10,0) NOT NULL,
  txn_amt double precision,
  txn_charge double precision,
  txn_comsn double precision
);

CREATE TABLE wallet_details
(
  id numeric(10,0) NOT NULL PRIMARY KEY,
  user_id numeric(10,0) NOT NULL,
  wallet_type numeric(10,0) NOT NULL,
  wallet_status numeric(10,0) DEFAULT 1,
  created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
  tot_acc_amt double precision
);

CREATE SEQUENCE seq_user_wallet INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 10 CACHE 1;
CREATE SEQUENCE seq_txn_passbook INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 10 CACHE 1;
CREATE SEQUENCE seq_txn_details INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 10 CACHE 1;
CREATE SEQUENCE seq_wallet_details INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 10 CACHE 1;

