package com.sk.bookz_customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class BookzCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookzCustomerApplication.class, args);
	}

}
