package com.example.wallet.common;

public class Constants {

	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int REBATE_VAR = -1;
	public static final int millis = 500;
	public static final int PENDING_ACC = 13;
	public static final String ROLE = "ADMIN";
	public static final String WALLETDATEPATTERN = "yyyy/MM/dd";

	public static final class Charges {

		public static final double CHARGE = 0.2;
		public static final double COMMISSION = 0.05;
		public static final double TOTALAMT = 1.25;
	}

	public static class ResponseConstants {

		public static final String SUCCESS = "SUCCESS";
		public static final String FAILURE = "FAILURE";
		public static final String PENDING = "PENDING";
	}

	public static class SecurityConstants {

		public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 1000 * 60 * 30 * 1;
		public static final String SIGNING_KEY = "walletSecretKey";
		public static final String TOKEN_PREFIX = "Wallet ";
		public static final String HEADER_STRING = "Authorization";
		public static final String AUTHORITIES_KEY = "scopes";
	}

	public static class TxnStatus {

		public static final int SUCCESS = 1;
		public static final int FAILED = 2;
		public static final int PENDING = 3;
	}

	public static class TxnType {

		public static final int DEBIT = 0;
		public static final int DEBIT_REBATE = 10;
		public static final int CREDIT = 1;
		public static final int CREDIT_REBATE = 11;
	}

	public static class WalletType {

		public static final int CLOSED = 1; // wallet to wallet
		public static final int OPEN = 2; // bank to bank
	}

	public static class WalletStatus {

		public static final int ACTIVE = 1;
		public static final int DORMANT = 0;
	}

}
