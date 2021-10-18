package com.krakenforce.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.krakenforce.app")
public class KrakenShopBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KrakenShopBackendApplication.class, args);
	}

}
