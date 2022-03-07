package com.example.wallet.common;

public class Constants {

	public static final int Zero = 0;

	public static final int One = 1;

	public static final String Role = "ADMIN";

	public static final String WalletDatePattern = "yyyy/MM/dd HH:mm:ss";

	public static final class Charges {

		public static final double Charge = 0.2;

		public static final double Commission = 0.05;

		public static final double TotalAmt = 1.25;
	}

	public static class ResponseConstants {

		public static String SUCCESS = "SUCCESS";

		public static String FAILURE = "FAILURE";

		public static String OK = "OK";

	}

	public static class SecurityConstants {

		public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 1000 * 60 * 30 * 1;

		public static final String SIGNING_KEY = "walletSecretKey";

		public static final String TOKEN_PREFIX = "Wallet ";

		public static final String HEADER_STRING = "Authorization";

		public static final String AUTHORITIES_KEY = "scopes";

	}

}
