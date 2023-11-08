package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		trafficServiceImpl.trafficInsert(form);
		return "redirect:/rpc/traffic/insert";
	}

}
