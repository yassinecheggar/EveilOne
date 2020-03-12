package com.upem.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@EntityScan( basePackages = {"com.upem.models"} )
@ComponentScan( basePackages = {"com.upem.controller"} )
@ComponentScan( basePackages = {"com.upem.services"} )
@EnableJpaRepositories("com.upem.repository")
@EnableAutoConfiguration
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class MqttAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqttAppApplication.class, args);
	}

}
