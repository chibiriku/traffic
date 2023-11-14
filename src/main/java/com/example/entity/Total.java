package com.example.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Total {
	private Long id;
	private Long userId;
	private Date monthDate;
	private Integer num;
}
