package com.java.springmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.springmongo.domain.User;
import com.java.springmongo.dto.UserDTO;
import com.java.springmongo.repository.UserRepository;
import com.java.springmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository rep;
	
	public List<User> findAll()
	{
		return rep.findAll();
	}
	

	public User findById(String id)
	{
		// A container object which may or may not contain a non-null value. 
		Optional<User> u = rep.findById(id);
		return u.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public User insert (User u)
	{
		return rep.insert(u);
	}
	
	public User fromDTO(UserDTO dto)
	{
		return new User(dto.getId(), dto.getName(), dto.getEmail());
	}
	
}
