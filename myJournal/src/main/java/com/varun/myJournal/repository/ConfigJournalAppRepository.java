package com.varun.myJournal.repository;

import com.varun.myJournal.entity.ConfigJournalAppEntity;
import com.varun.myJournal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {



    }



    //this Repository is Autowired in AppCache Bean (or) Component

