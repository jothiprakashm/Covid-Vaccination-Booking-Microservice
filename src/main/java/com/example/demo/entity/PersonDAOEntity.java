package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Data;


@Entity
@Table(name="Registration" , uniqueConstraints={@UniqueConstraint(columnNames ={"aadhar_no"})})
@Data
public class PersonDAOEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "person_id_seq")
	@SequenceGenerator(sequenceName = "person_id_seq", allocationSize = 1, name = "person_id_seq")
	@Column
	private int id;
	@Column
	private String name;
	@Column(name="aadhar_no")
	private String aadharNo;
	@Column
	private String phoneNo;
	@Column
	private String dob;
	
	

}
