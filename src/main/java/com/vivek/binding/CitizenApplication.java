package com.vivek.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CitizenApplication {
	
	private String fullName;
	private String email;
	private long phoneNum;
	private Integer Ssn;
	private String gender;
	private LocalDate dob;

}
