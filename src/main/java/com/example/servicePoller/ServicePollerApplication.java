package com.example.servicePoller;

import com.example.servicePoller.models.ServiceDetail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ServicePollerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicePollerApplication.class, args);
	}

}
