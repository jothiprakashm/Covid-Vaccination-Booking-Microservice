package com.example.demo.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.PersonRegistrationInput;
import com.example.demo.entity.PersonDAOEntity;
import com.example.demo.repository.RegistrationDAO;

@Service
public class RegistrationServiceImpl implements RegistrationService{
	
	@Autowired
    RegistrationDAO registrationDAO;
    
	@Autowired
	ModelMapper modelMapper;
	

	@Override
	public int register(PersonRegistrationInput personRegistrationInput) throws Exception {
		int id = 0;
		try {
			PersonDAOEntity response = registrationDAO.save(this.modelMapper.map(personRegistrationInput, PersonDAOEntity.class));
			id = response.getId();
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return id;
	}


	@Override
	public boolean validateAadharAndPhoneNo(PersonRegistrationInput personRegistrationInput) throws Exception {
		if(personRegistrationInput.getAadharNo().length() == 12 && personRegistrationInput.getPhoneNo().length() == 10) {
			return true;
		}
		return false;
	}


	@Override
	public boolean validateAge(PersonRegistrationInput personRegistrationInput) throws Exception {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate dob = LocalDate.parse(personRegistrationInput.getDob() , formatter);
		int age = Period.between(dob, today).getYears();
		System.out.println(age);
		if(age >= 45) {
			return true;
		}
		return false;
	}

}
