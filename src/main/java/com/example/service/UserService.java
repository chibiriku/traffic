package com.example.service;

import java.util.List;

import com.example.entity.Users;
import com.example.form.UserAddForm;

public interface UserService {
	
	void userAdd(UserAddForm form);

	List<Users> userList();

	List <Users> trafficList(int id);
	
	Users findLoginUser(String name);


}
