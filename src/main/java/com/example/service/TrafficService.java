package com.example.service;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.entity.Traffic;
import com.example.entity.Users;
import com.example.form.TrafficInsertForm;

public interface TrafficService {

	void trafficInsert(TrafficInsertForm form);

	Long getId();

	Map<YearMonth, Integer> calculateMonthlyCost(List<Traffic> trafficList);

	List<Traffic> currentTraffics();

	int total(YearMonth month);

	List<Traffic> loginTrafficList(List<Users> list);

	List<Traffic> monthlyTrafficList(int id, int year,int month);

	int monthlyTotal(int id, int year, int month);

	Traffic trafficOne(int id);

	void trafficUpdate(Long id,
			Date useday,
			String means,
			String secter,
			String road,
			int cost);
	
	void trafficDelete(Long id);

}
