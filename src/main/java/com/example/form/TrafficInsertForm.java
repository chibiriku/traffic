package com.example.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TrafficInsertForm {
	
	@NotNull
	@DateTimeFormat(pattern="yyyyMMdd")
	private Date useday;

	private Long userId;
	
	@NotBlank
	private String means;
	
	@NotBlank
	private String secter;
	
	@NotBlank
	private String road;
	
	@NotNull
	private Integer cost;
}
