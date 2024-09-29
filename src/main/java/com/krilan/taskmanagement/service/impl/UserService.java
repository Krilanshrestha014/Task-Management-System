package com.krilan.taskmanagement.service.impl;

import java.util.List;

import com.krilan.taskmanagement.entity.User;

public interface UserService {

		void addUser(User user);

		void updateUser(User user);

		void deleteUser(int id);

		List<User> getAllUser();

		User getUserById(int id);
		User findByEmail(String email);

		
}
