package com.example.form;

import java.util.Date;

import lombok.Data;

@Data
public class UserAddForm {

	private String name;
	private String password;
	private String mail;
	private Date createDate;

}
