package com.example.demo.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CancellationInput {
	
	private String beneficiaryId;
	private String dose;

}
