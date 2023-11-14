package com.example.controller;

import java.util.ArrayList;
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
import com.example.entity.Users;
import com.example.form.UserAddForm;
import com.example.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/rpc")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	//社員追加フォーム
	@GetMapping("/add")
	public String userAddForm(Model model) {
		model.addAttribute("form" ,new UserAddForm());
		return "/user/add";
	}
	@PostMapping("/add")
	public String userAdd(@ModelAttribute UserAddForm form, Model model) {
		userServiceImpl.userAdd(form);
		return "redirect:/rpc/add";
	}

	//社員リスト
	@GetMapping("/list")
	public String userList(Model model) {
		List<Users> list = userServiceImpl.userList();
		model.addAttribute("list", list);
		return "user/list";
	}

	//社員詳細
	@GetMapping("/{id}")
	public String userDetail(@PathVariable(name = "id") int id, Model model) {
		List <Users> tlist = userServiceImpl.trafficList(id);
		List<Traffic> list = new ArrayList<>();
	    for (Users user : tlist) {
	        List<Traffic> userTrafficList = user.getTrafficList();
	        if (userTrafficList != null) {
	            list.addAll(user.getTrafficList());
	        }
	    }
	    model.addAttribute("tlist", list);
	    return "/user/trafficList";
	}



}
