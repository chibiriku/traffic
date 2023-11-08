package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Users;
import com.example.form.UserAddForm;
import com.example.repository.UserMapper;

@Service
public class UserServiceImple {

	@Autowired
	private UserMapper userMapper;

	public void userAdd(UserAddForm form) {
		userMapper.userAdd(form);
	}

	public List<Users> userList(){
		return userMapper.userList();
	}

	public List<Users> trafficList(int id){
		return userMapper.trafficList(id);
	}

}
