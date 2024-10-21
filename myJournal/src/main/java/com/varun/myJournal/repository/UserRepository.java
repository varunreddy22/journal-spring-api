package com.varun.myJournal.repository;

import com.varun.myJournal.entity.JournalEntry;
import com.varun.myJournal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

      User findByUserName(String username);

      void deleteByUserName(String username);

    }

    //Query method DSL: using a method name as a means of query to fetch data
//Eg: findByUserName()--> fetches the user name based on the argument passed
