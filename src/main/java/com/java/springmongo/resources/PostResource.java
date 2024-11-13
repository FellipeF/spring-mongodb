package com.java.springmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.springmongo.domain.Post;
import com.java.springmongo.resources.util.URL;
import com.java.springmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService service;	//REST Controller has access to UserService, that has access to the Repository
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id)
	{	
		Post p = service.findById(id);
		return ResponseEntity.ok().body(p);
	}
	
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text)
	{	
		text = URL.decodeParam(text);
		List<Post> posts = service.findByTitle(text);
		
		return ResponseEntity.ok().body(posts);
	}
}
