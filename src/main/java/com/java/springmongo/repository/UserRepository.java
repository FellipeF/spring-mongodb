package com.java.springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.java.springmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
}
