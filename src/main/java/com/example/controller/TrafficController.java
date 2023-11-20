package com.example.controller;

import java.time.Year;
import java.time.YearMonth;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Traffic;
import com.example.form.TrafficInsertForm;
import com.example.form.TrafficUpdateForm;
import com.example.service.impl.TrafficServiceImpl;

@Controller
@RequestMapping("/rpc")
public class TrafficController {

	@Autowired
	private TrafficServiceImpl trafficServiceImpl;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/traffic/insert")
	public String trafficInsertForm(Model model, @ModelAttribute TrafficInsertForm form) {
		model.addAttribute("form", form);
		return "traffic/insert";
	}

	@PostMapping("/traffic/insert")
	public String trafficInsert(@ModelAttribute @Validated TrafficInsertForm form, BindingResult result, Model model) {
		
		if(!result.hasErrors()) {
			Long id = trafficServiceImpl.getId();
			form.setUserId(id);
			trafficServiceImpl.trafficInsert(form);
			return "redirect:/rpc/traffic/list";	
			}else {
			return trafficInsertForm(model, form);
		}
	}

//	//今月の交通費一覧
//	@GetMapping("/traffic/list")
//	public String currentList(Model model) {
//		List <Traffic> currentList = trafficServiceImpl.currentTraffics();
//		YearMonth currentYearMonth = YearMonth.now();
//		int total = trafficServiceImpl.total(currentYearMonth);
//		model.addAttribute("list", currentList);
//		model.addAttribute("total", total);
//		return "traffic/list";
//	}
//
//
//	//月別の交通費一覧
//	@GetMapping("traffic/{month}")
//	public String trafficList(@PathVariable int month ,Model model) {
//		Year currentYear = Year.now();
//		int year = currentYear.getValue();
//		int id = Math.toIntExact(trafficServiceImpl.getId());
//		List<Traffic> list = trafficServiceImpl.monthlyTrafficList(id, year, month);
//		int total = trafficServiceImpl.monthlyTotal(id, year, month);
//		model.addAttribute("list", list);
//		model.addAttribute("total", total);
//		return "traffic/list";
//	}
	
	@GetMapping("traffic/list")
	public String trafficList(@RequestParam(name = "month", required = false, defaultValue = "0") int month, Model model) {
	    if (month == 0) {
	        month = YearMonth.now().getMonthValue();
	        model.addAttribute("month", month);
	    }

	    Year currentYear = Year.now();
	    int year = currentYear.getValue();
	    int id = Math.toIntExact(trafficServiceImpl.getId());
	    List<Traffic> list = trafficServiceImpl.monthlyTrafficList(id, year, month);
	    int total = trafficServiceImpl.monthlyTotal(id, year, month);
	    model.addAttribute("list", list);
	    model.addAttribute("total", total);
	    return "traffic/list";
	}

	//交通費レコードの詳細
	@GetMapping("/traffic/detail/{id}")
	public String trafficDetail(@PathVariable int id, Model model, TrafficUpdateForm form) {
		Traffic traffic = trafficServiceImpl.trafficOne(id);
		form = modelMapper.map(traffic, TrafficUpdateForm.class);
		model.addAttribute("form", form);
		return "traffic/detail";
	}

	//更新処理
	@PostMapping(value = "/traffic/detail", params = "update")
	public String trafficUpdate(TrafficUpdateForm form, Model model) {
		trafficServiceImpl.trafficUpdate(form.getId(),form.getUseday(),form.getMeans(),form.getSecter(),form.getRoad(),form.getCost());
		return "redirect:/rpc/traffic/list";
	}
	
	//削除処理
	@PostMapping(value = "/traffic/detail", params = "delete")
	public String trafficDelete(TrafficUpdateForm form, Model model) {
		trafficServiceImpl.trafficDelete(form.getId());
		return "redirect:/rpc/traffic/list";
	}
}
