package com.example.demo.repo;

import com.example.demo.models.commentStruct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Repeatable;

@Repository
public interface commentStructInterface extends MongoRepository<commentStruct, Integer> {
}
