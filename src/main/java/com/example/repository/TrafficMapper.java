package com.example.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.form.TrafficInsertForm;

@Mapper
public interface TrafficMapper {

	void trafficInsert(TrafficInsertForm form);
	
}
