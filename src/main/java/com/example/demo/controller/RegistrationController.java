package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.RegistrationConstants;
import com.example.demo.domain.PersonRegistrationInput;
import com.example.demo.service.RegistrationService;


@RestController
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> processRegistration(@RequestBody PersonRegistrationInput personRegistrationInput) {
		String response = null;
		try {
			if(registrationService.validateAadharAndPhoneNo(personRegistrationInput)) {
				if(registrationService.validateAge(personRegistrationInput)) {
					int id = registrationService.register(personRegistrationInput);
					response = RegistrationConstants.REGISTRATION_SUCCESS + id;
				}
				else {
					response = RegistrationConstants.INVALID_AGE;
				}
			}
			else {
				response = RegistrationConstants.INVALID;
			}
		}
		catch(Exception e) {
			return new ResponseEntity<>(RegistrationConstants.NOT_UNIQUE_AADHAR, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
