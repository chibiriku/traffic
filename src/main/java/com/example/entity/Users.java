package com.example.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Users {
	private Long id;
	private String name;
	private String password;
	private String mail;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
}
