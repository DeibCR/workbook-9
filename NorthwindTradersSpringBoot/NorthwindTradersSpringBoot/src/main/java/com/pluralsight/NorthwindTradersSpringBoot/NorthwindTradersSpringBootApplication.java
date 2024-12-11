package com.pluralsight.NorthwindTradersSpringBoot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.pluralsight.NorthwindTradersSpringBoot.dao")
public class NorthwindTradersSpringBootApplication  {
	public static void main(String[] args) {
		SpringApplication.run(NorthwindTradersSpringBootApplication.class, args);
		System.out.println("Northwind Traders REST API is running!");
	}
}

