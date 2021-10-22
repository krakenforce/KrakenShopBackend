package com.krakenforce.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.krakenforce.app.config.FileStorageProperties;


@SpringBootApplication(scanBasePackages = "com.krakenforce.app")
@ConfigurationPropertiesScan
@EnableConfigurationProperties(FileStorageProperties.class)
public class KrakenShopBackendApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(KrakenShopBackendApplication.class, args);
		
	}


}
