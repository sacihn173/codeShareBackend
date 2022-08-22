package com.example.demo.repo;

import com.example.demo.models.largestKey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface largestKeyInterface extends MongoRepository<largestKey, Integer> {
}
