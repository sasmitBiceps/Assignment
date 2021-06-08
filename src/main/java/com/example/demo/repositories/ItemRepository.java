package com.example.demo.repositories;

import com.example.demo.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    //We don't need to implement anything here, spring does this for us.
    // MongoRepository has pre-defined functions which are enough for us.
}
