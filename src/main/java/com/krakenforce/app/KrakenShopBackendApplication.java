package com.krakenforce.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.krakenforce.app")
public class KrakenShopBackendApplication implements CommandLineRunner {

	
	public static void main(String[] args) {
		SpringApplication.run(KrakenShopBackendApplication.class, args);
		
//		PaymentContext context = new PaymentContext();
//		context.set_paymentRouteStrategy(new MomoPaymentBehaviour());
//		context.executePayment();
		
	}

	@Override
	public void run(String... args) throws Exception {
//		storageService.deleteAll();
		//fileService.init();
		
	}
	
	

}
