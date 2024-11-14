package com.java.springmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.springmongo.domain.Post;
import com.java.springmongo.repository.PostRepository;
import com.java.springmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository rep;
	
	public Post findById (String id)
	{
		Optional<Post> p = rep.findById(id);
		return p.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public List<Post> findByTitle(String text)
	{
		//return rep.findByTitleContainingIgnoreCase(text);
		return rep.searchTitle(text);
	}
	
	public List<Post> fullSearch (String text, Date minDate, Date maxDate)
	{
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);	//Grants that the posts searched are also from this day.
		return rep.fullSearch(text, minDate, maxDate);
	}
	
}
