package com.java.springmongo.repository;

import java.util.Date;
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
	
	//Query with many criteria
	//Search for the text anywhere where date is greater than min and less that max
	@Query("{ $and: [ {date: {$gte: ?1} }, { date: { $lte: ?2} }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")	
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	
}
