package com.example.wallet.common;

public class RuleUtil {

	public double actualBalance( double amount ) {
		return amount / Constants.Charges.TotalAmt;
	}

	public double calculateCommission( double amount ) {
		amount = actualBalance( amount );
		return amount * Constants.Charges.Commission;
	}

	public double calculateCharge( double amount ) {
		amount = actualBalance( amount );
		return amount * Constants.Charges.Charge;
	}

	public double calculateExtra( double amount ) {
		return amount - actualBalance( amount );
	}

}
