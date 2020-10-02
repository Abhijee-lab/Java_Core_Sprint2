package com.capgemini.onlinetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableTransactionManagement

public class OnlineTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineTestApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
//
