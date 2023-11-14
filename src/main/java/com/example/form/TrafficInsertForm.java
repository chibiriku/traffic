package com.example.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TrafficInsertForm {

	@DateTimeFormat(pattern="yyyyMMdd")
	private Date useday;
	
	private Long userId;

	private String means;
	private String secter;
	private String road;
	private Integer cost;

}
