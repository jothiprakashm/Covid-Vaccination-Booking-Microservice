package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="Booking")
@Data
@ToString
public class BookingDAOEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "booking_id_seq")
	@SequenceGenerator(sequenceName = "booking_id_seq", allocationSize = 1, name = "booking_id_seq")
	@Column 
	private int bookingId;
	@Column
	private String beneficiaryId;
	@Column
	private String date;
	@Column
	private String timeSlot;
	@Column
	private String dose;
	@Column
	private String vaccinationCentre;

}
