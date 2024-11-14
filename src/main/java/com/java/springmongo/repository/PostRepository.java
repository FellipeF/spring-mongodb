package com.java.springmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.springmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	//Query Methods
	List <Post> findByTitleContainingIgnoreCase(String text);
	
	//Personalized Query Method (MongoDB Query with JSON Formatting)
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")	//Case insensitivity using the first parameter
	List<Post> searchTitle(String text);
	
}
