package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.form.TrafficInsertForm;
import com.example.repository.TrafficMapper;

@Service
public class TrafficServiceImpl {

	@Autowired
	private TrafficMapper trafficMapper;

	public void trafficInsert(TrafficInsertForm form) {
		trafficMapper.trafficInsert(form);
	}
}
