package com.java.springmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.springmongo.domain.User;
import com.java.springmongo.dto.UserDTO;
import com.java.springmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;	//REST Controller has access to UserService, that has access to the Repository
	
	@RequestMapping(method = RequestMethod.GET)	//Alternative to GetMapping
	public ResponseEntity<List<UserDTO>> findAll()
	{	
		List <User> list = service.findAll();
		List <UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
}
