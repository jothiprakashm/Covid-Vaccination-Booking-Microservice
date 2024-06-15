package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PersonDAOEntity;

@Repository
public interface RegistrationDAO extends JpaRepository<PersonDAOEntity, Integer> {
	
}
