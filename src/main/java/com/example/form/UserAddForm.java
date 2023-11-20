package com.example.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserAddForm {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String mail;
	
	private Date createDate;

}
