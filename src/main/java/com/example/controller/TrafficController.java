package com.example.controller;

import java.time.Year;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Traffic;
import com.example.form.TrafficInsertForm;
import com.example.service.impl.TrafficServiceImpl;

@Controller
@RequestMapping("/rpc")
public class TrafficController {

	@Autowired
	private TrafficServiceImpl trafficServiceImpl;

	@GetMapping("/traffic/insert")
	public String trafficInsertForm(Model model) {
		model.addAttribute("form", new TrafficInsertForm());
		return "traffic/insert";
	}

	@PostMapping("/traffic/insert")
	public String trafficInsert(@ModelAttribute TrafficInsertForm form, Model model) {
		Long id = trafficServiceImpl.getId();
		form.setUserId(id);
		trafficServiceImpl.trafficInsert(form);
		return "redirect:/rpc/traffic/insert";
	}
	
	@GetMapping("traffic/list")
	public String currentList(Model model) {
		List <Traffic> currentList = trafficServiceImpl.currentTraffics();
		YearMonth currentYearMonth = YearMonth.now();
		int total = trafficServiceImpl.total(currentYearMonth);
		model.addAttribute("list", currentList);
		model.addAttribute("total", total);
		return "traffic/list";
	}
	
	@GetMapping("traffic/{month}")
	public String trafficList(@PathVariable int month ,Model model) {
		Year currentYear = Year.now();
		int year = currentYear.getValue();
		int id = Math.toIntExact(trafficServiceImpl.getId());
		List<Traffic> list = trafficServiceImpl.monthlyTrafficList(id, year, month);
		int total = trafficServiceImpl.monthlyTotal(id, year, month);
		model.addAttribute("list", list);
		model.addAttribute("total", total);
		return "traffic/list";
	}

}
