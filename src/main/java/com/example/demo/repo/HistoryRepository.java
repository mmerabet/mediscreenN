package com.example.demo.repo;

import com.example.demo.model.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoryRepository extends MongoRepository<History, Integer> {
    Optional<History> findById(String id);

    boolean existsById(String id);

    void deleteHistoryById(String id);
}
