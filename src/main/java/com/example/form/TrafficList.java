package com.example.form;

import java.util.List;

import com.example.entity.Traffic;

import lombok.Data;

@Data
public class TrafficList {
	private String name;
	private List<Traffic> list;

}