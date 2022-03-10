package com.example.wallet.common;

import java.util.UUID;

public class RuleUtil {

	public String getTxnId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public double actualBalance( double amount ) {
		return amount / Constants.Charges.TOTALAMT;
	}

	public double calculateCommission( double amount ) {
		amount = actualBalance( amount );
		return amount * Constants.Charges.COMMISSION;
	}

	public double calculateCharge( double amount ) {
		amount = actualBalance( amount );
		return amount * Constants.Charges.CHARGE;
	}

	public double calculateExtra( double amount ) {
		return amount - actualBalance( amount );
	}

}
