package com.example.demo.repo;

import com.example.demo.models.commentStruct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Repeatable;
import java.util.List;

@Repository
public interface commentStructInterface extends MongoRepository<commentStruct, Integer> {

    @Query(value = "{ 'postId' : ?0}")
    List<commentStruct> findByPostId(int postId);

}
