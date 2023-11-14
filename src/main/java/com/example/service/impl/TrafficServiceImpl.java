package com.example.service.impl;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Traffic;
import com.example.entity.Users;
import com.example.form.TrafficInsertForm;
import com.example.repository.TrafficMapper;
import com.example.service.TrafficService;

@Service
public class TrafficServiceImpl implements TrafficService {

	@Autowired
	private TrafficMapper trafficMapper;
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Override
	public void trafficInsert(TrafficInsertForm form) {
		trafficMapper.trafficInsert(form);
	}
	
	@Override
	public Long getId() {
		Users loginUser = userServiceImpl.loginUser();
		Long id = loginUser.getId();
		return id;
	}
	
	//型変換List<Users>→List<traffic>
		public List<Traffic> loginTrafficList(List<Users> userList) {
			List<Traffic> tlist = new ArrayList<>();
		    for (Users user : userList) {
		        List<Traffic> userTrafficList = user.getTrafficList();
		        if (userTrafficList != null) {
		            tlist.addAll(user.getTrafficList());
		        }
		    }
			return tlist;
		}
	
	//月ごとの交通費合計値一覧
	@Override
	public Map<YearMonth, Integer> calculateMonthlyCost(List<Traffic> trafficList){
	    Map<YearMonth, Integer> monthlyCost = new HashMap<>();
	    
	    Map<YearMonth, List<Traffic>> groupedByMonth = trafficList.stream().collect(Collectors.groupingBy(traffic -> YearMonth.from(traffic.getUsedayAsLocalDate())));

	    for (Map.Entry<YearMonth, List<Traffic>> entry : groupedByMonth.entrySet()) {
	        int totalCost = entry.getValue().stream().mapToInt(Traffic::getCost).sum();
	        monthlyCost.put(entry.getKey(), totalCost);
	    }

	    return monthlyCost;
	}

	//今月の交通費リスト
	@Override
	public List<Traffic> currentTraffics(){
		Long id = getId();
		List<Users> tlist = userServiceImpl.currentTrafficList(id);
		return loginTrafficList(tlist);
	}
	
	//月ごとの交通費リスト
	@Override
	public List<Traffic> monthlyTrafficList(int id,int year,int month) {
		
		return loginTrafficList(userServiceImpl.monthlyTrafficList(id, year, month));
		
	}
	
	//今月の合計
	@Override
	public int total(YearMonth month) {
		
		List<Traffic> trafficList = loginTrafficList(userServiceImpl.currentTrafficList(getId()));
	    Map<YearMonth, Integer> monthlyCost = calculateMonthlyCost(trafficList);
	    
	    return monthlyCost.get(month);
	}
	
	//月ごとの合計
	@Override
	public int monthlyTotal(int id, int year, int month) {
		List<Traffic> monthlyTraffics = monthlyTrafficList(id, year, month);
		int num = 0;
		for (Traffic traffic : monthlyTraffics) {
			num =+ traffic.getCost();
		}
		return num;
	}
	
	
}
