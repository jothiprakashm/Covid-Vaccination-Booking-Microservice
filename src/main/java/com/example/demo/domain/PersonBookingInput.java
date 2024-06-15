package com.example.demo.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PersonBookingInput {

		private String beneficiaryId;
		private String date;
		private String timeSlot;
		private String dose;
		private String vaccinationCentre;
}
