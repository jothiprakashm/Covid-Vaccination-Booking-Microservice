package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.repository.RegistrationDAO;


@SpringBootApplication
@EnableJpaRepositories("com.example.demo.repository")
@EntityScan("com.example.demo.entity")
@ComponentScan(basePackages = {"com.example.demo.*"}, basePackageClasses = RegistrationDAO.class)
public class DemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Started");
	}
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
