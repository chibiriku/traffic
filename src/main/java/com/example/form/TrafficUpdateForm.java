package com.example.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TrafficUpdateForm {

	private Long id;
	@DateTimeFormat(pattern = "yyyyMMdd")
	private Date useday;
	private String means;
	private String secter;
	private String road;
	private Integer cost;

}
