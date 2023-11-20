package com.example.controller;

import java.util.ArrayList;
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

import com.example.entity.Traffic;
import com.example.entity.Users;
import com.example.form.UserAddForm;
import com.example.form.UserUpdateForm;
import com.example.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/rpc")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private ModelMapper modelMapper;

	//社員追加フォーム
	@GetMapping("/add")
	public String userAddForm(Model model, @ModelAttribute UserAddForm form) {
		model.addAttribute("form",form);
		return "/user/add";
	}
	
	@PostMapping("/add")
	public String userAdd(@ModelAttribute @Validated UserAddForm form, BindingResult result, Model model) {
		if(!result.hasErrors()) {
			userServiceImpl.userAdd(form);
			return "redirect:/rpc/list";	
			}else {
			return userAddForm(model, form);
		}
	}
	
	//社員更新フォーム
	@GetMapping("/detail/{id}")
	public String userDetail(@PathVariable int id, Model model, UserUpdateForm form) {
		Users user = userServiceImpl.userOne(id);
		form = modelMapper.map(user, UserUpdateForm.class);
		model.addAttribute("form", form);
		return "user/edit";
	}
	
	//社員更新
	@PostMapping(value = "/detail", params = "update")
	public String userDetail(UserUpdateForm form, Model model) {
		userServiceImpl.userUpdate(form.getId(),form.getName(),form.getMail());
		return "redirect:/rpc/list";
	}
	
	//社員削除
		@PostMapping(value = "/detail", params = "delete")
		public String userDelete(UserUpdateForm form, Model model) {
			userServiceImpl.userDelete(form.getId());
			return "redirect:/rpc/list";
		}

	//社員リスト
	@GetMapping("/list")
	public String userList(Model model) {
		List<Users> list = userServiceImpl.userList();
		List<Users> checkedList = new ArrayList<>();
		for (Users user : list) {
			if (user.getDeleteDate() == null) {
				user.setCheck(1);
				checkedList.add(user);
			}else {
				user.setCheck(2);
				checkedList.add(user);
			}
		}
		model.addAttribute("list", checkedList);
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
