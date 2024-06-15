package com.example.demo.domain;


import lombok.Data;

@Data
public class PersonRegistrationInput {
	
	private String name;
	private String aadharNo;
	private String phoneNo;
	private String dob;
	
}
