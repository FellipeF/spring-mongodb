package com.java.springmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id)
	{	
		User u = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(u));
	}
	
	@PostMapping
	public ResponseEntity <Void> insert(@RequestBody UserDTO dto)
	{	
		User u = service.fromDTO(dto);
		u = service.insert(u);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();
		return ResponseEntity.created(uri).build();	//201 Code.
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id)
	{	
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseEntity <Void> update(@RequestBody UserDTO dto, @PathVariable String id)
	{	
		User u = service.fromDTO(dto);
		u.setId(id);
		u = service.update(u);
		
		return ResponseEntity.noContent().build();
	}
}
