package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Users;
import com.example.form.UserAddForm;
import com.example.repository.UserMapper;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

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

	//ログイン中メールアドレス
	@Override
	public String loginUserName() {
		Authentication user = SecurityContextHolder.getContext().getAuthentication();
		String mail = user.getName();
		return mail;
	}

	//ログイン中ユーザー情報
	@Override
	public Users loginUser() {
		Users loginUser = userMapper.findLoginUser(loginUserName());
		return loginUser;
	}

	//今月の交通費リスト
	@Override
	public List<Users> currentTrafficList(Long id) {
		return userMapper.currentTrafficList(id);
	}

	//月ごとの交通費リスト
	@Override
	public List<Users> monthlyTrafficList(int id,int year,int month) {
		return userMapper.monthlyTrafficList(id ,year, month);
	}

}
