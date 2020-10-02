package com.capgemini.onlinetest.service;

import java.util.List;

import com.capgemini.onlinetest.entities.User;



public interface IUserService {
	User addUser(User user);

	User findById(Long userId);

	User updateUser(Long userId, User user);
	
	User deleteUser(Long userId);
	
	List<User> fetchAll();

}
//