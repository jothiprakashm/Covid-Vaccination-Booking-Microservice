package com.example.demo.service;

import com.example.demo.domain.PersonRegistrationInput;

public interface RegistrationService {
	
	public int register(PersonRegistrationInput personRegistrationInput) throws Exception;
	public boolean validateAadharAndPhoneNo(PersonRegistrationInput personRegistrationInput) throws Exception;
	public boolean validateAge(PersonRegistrationInput personRegistrationInput) throws Exception;
}
