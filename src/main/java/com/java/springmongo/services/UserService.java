package com.java.springmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.springmongo.domain.User;
import com.java.springmongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository u;
	
	public List<User> findAll()
	{
		return u.findAll();
	}
	
}
