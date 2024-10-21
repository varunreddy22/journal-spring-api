package com.varun.myJournal.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection="users")
//this will check the collection named journal_entries in db
//if that collection is not found, it will create one.
@Data
@Builder //The Builder Pattern allows the construction of complex objects step-by-step. It's particularly useful for creating immutable objects or objects that require many parameters.
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private ObjectId id;

    @Indexed(unique= true) //searching becomes fast
    @NonNull
    private String userName;
    private String email;
    private boolean sentimentAnalysis;
    @NonNull
   private String password;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
    private List<String> roles;


}

// user, pwd should be non null,
//NonNull --> this is an annotation that does not allow null values for username
//and password.
//when we set a value for username using setter(which is done by lombok).
// The annotation processor checks for null values.  if these fields
// contains null values, it throws NullPointerException. and that field will not
//be saved in the database.


//Every User will have multiple JournalEntries
//I want to link those JournalEntry's to a particular User.
//Hence, I have created a collection for users in journaldb

//DBRef annotation allows us to create "JournalEntry's" reference in "users" collection.
//This works as a foreign key.
//this acts as a link between "journal_entries" and "users" collection.
//we only used "Id" reference of each "JournalEntry" of a particular user.


//we added few more fields in User like email, sentimentAnalysis.
//sentimentAnalysis is a boolean type.
//Who ever user opt in for the sentimentAnalysis, every weekend we send a mail
//to the user about his mood last week.(We can use NLP or ML)
//(by reading all his journal entries that week, we can determine his mood).
