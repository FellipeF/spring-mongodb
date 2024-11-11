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
	
	public void delete (String id)
	{
		findById(id);
		rep.deleteById(id);
	}
	
	public User update(User u)
	{
		User newUser = findById(u.getId());
		updateData(newUser, u);	//Copy from u to newUser
		return rep.save(newUser);
	}


	private void updateData(User newUser, User u) {
		newUser.setName(u.getName());
		newUser.setEmail(u.getEmail());
	}


	public User fromDTO(UserDTO dto)
	{
		return new User(dto.getId(), dto.getName(), dto.getEmail());
	}
	
}
