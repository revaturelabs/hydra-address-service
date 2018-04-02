package com.revature.hydra.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan
public class AddressRepositoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressRepositoryServiceApplication.class, args);
	}

}
