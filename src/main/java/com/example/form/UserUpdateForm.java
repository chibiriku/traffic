package com.example.form;

import lombok.Data;

@Data
public class UserUpdateForm {
	private Long id;
	private String name;
	private String password;
	private String mail;
}
