package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.Users;
import com.example.form.UserAddForm;

@Mapper
public interface UserMapper {

	void userAdd(UserAddForm form);

	List<Users> userList();

	List <Users> trafficList(int id);
	
	Users findLoginUser(String name);
	
	List <Users> currentTrafficList(Long id);
	
	List <Users> monthlyTrafficList(int id,int year,int month);
	
}
