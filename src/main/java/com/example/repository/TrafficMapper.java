package com.example.repository;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.entity.Traffic;
import com.example.form.TrafficInsertForm;

@Mapper
public interface TrafficMapper {

	void trafficInsert(TrafficInsertForm form);

	Traffic trafficOne(int id);

	void trafficUpdate(@Param("id") Long id,
			@Param("useday") Date useday,
			@Param("means") String means,
			@Param("secter") String secter,
			@Param("road") String road,
			@Param("cost") int cost);
	
	void trafficDelete(Long id);

}
