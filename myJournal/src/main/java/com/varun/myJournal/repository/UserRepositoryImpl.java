package com.varun.myJournal.repository;

import com.varun.myJournal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.schema.JsonSchemaObject;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;


    public List<User> getUserForSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

        List<User> users = mongoTemplate.find(query, User.class);//ORM
        return users;

    }
}


//"MongoRepository": Previously in UserRepository, we were accessing the data from db by extending MongoRepository.
//"Criteria": Now in this class, we want to create our custom query to get specific data, so we can do this by using Criteria

//Criteria and Query goes hand-in-hand
//You will be able to run a Criteria using a Query

//MongoTemplate is a class provided by Spring.
//used to interact with db


// mongoTemplate.find(query, User.class);
//the above will return all the users that satisfies the Criteria.
//the User.class indicates that the query should be applied on User entity


//query.addCriteria(Criteria.where("email").exists(true));
//query.addCriteria(Criteria.where("email").ne(null).ne(""));
//query.addCriteria(Criteria.where("sentimentAnalysis").type(JsonSchemaObject.Type.booleanType()));
//query.addCriteria(Criteria.where("sentimentAnalysis").type(JsonSchemaObject.Type.BsonType.BOOLEAN));


//We can do be by using @Scheduler"
//We are also writing an automation code, where every week on Sunday at 9:00 am
//all these users who opted for sentimentAnalysis should get an email about their sentiment.
//To implement the above we first create a package called "scheduler".
