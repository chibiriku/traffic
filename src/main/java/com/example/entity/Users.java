package com.example.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Users {
	private Long id;
	private String name;
	private String password;
	private String mail;
	private String role;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;

	private List<Traffic> trafficList;
}
