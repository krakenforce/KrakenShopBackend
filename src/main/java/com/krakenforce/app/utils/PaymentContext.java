package com.krakenforce.app.utils;

import com.krakenforce.app.repository.payment.PaymentRouteStrategy;

public class PaymentContext {
	
	private PaymentRouteStrategy _paymentRouteStrategy;
	
	
	
	public PaymentRouteStrategy get_paymentRouteStrategy() {
		return _paymentRouteStrategy;
	}



	public void set_paymentRouteStrategy(PaymentRouteStrategy _paymentRouteStrategy) {
		this._paymentRouteStrategy = _paymentRouteStrategy;
	}



	public void executePayment() {
		_paymentRouteStrategy.doPayment();
	}
	
}
