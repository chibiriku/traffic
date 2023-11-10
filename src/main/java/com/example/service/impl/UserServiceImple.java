package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Users;
import com.example.form.UserAddForm;
import com.example.repository.UserMapper;
import com.example.service.UserService;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public void userAdd(UserAddForm form) {
		String rawPassword = form.getPassword();
		form.setPassword(encoder.encode(rawPassword));
		
		userMapper.userAdd(form);
	}
	
	@Override
	public List<Users> userList(){
		return userMapper.userList();
	}
	
	@Override
	public List<Users> trafficList(int id){
		return userMapper.trafficList(id);
	}

	@Override
	public Users findLoginUser(String mail) {
		return userMapper.findLoginUser(mail);
	}

}
