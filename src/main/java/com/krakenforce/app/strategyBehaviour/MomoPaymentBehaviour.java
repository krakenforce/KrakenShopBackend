package com.krakenforce.app.strategyBehaviour;

import com.krakenforce.app.repository.payment.PaymentRouteStrategy;

public class MomoPaymentBehaviour implements PaymentRouteStrategy {

	@Override
	public void doPayment() {
		System.out.print("Payment with MOMO");
		
	}

}
