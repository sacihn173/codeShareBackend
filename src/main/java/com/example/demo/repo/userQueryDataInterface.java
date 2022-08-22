package com.example.demo.repo;

import com.example.demo.models.userQueryData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userQueryDataInterface extends MongoRepository<userQueryData, Integer> {
}
