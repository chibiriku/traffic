package com.example.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Traffic {
	private Long id;
	private Long userId;
	private Date userDay;
	private String means;
	private String secter;
	private String road;
	private Integer cost;
}