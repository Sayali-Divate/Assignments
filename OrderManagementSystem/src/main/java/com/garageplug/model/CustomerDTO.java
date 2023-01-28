package com.garageplug.model;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerDTO {
	
//	@Pattern(regexp = "^[a-zA-Z]$", message = "Please Enter valid first name")
	private String firstName;
	
//	@Pattern(regexp = "^[a-zA-Z]$", message = "Please Enter valid last name")
	private String lastName;
	
	@Email	
	private String email;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
	@Transient
	private String password;
}
