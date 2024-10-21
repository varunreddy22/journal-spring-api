package com.varun.myJournal.service;

import com.varun.myJournal.entity.JournalEntry;
import com.varun.myJournal.entity.User;
import com.varun.myJournal.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//@Component
@Service
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    //in below saveEntry method we are saving the new JournalEntry in journal_entries
    // collection and then also in users collection.

    //In the context of database operations, transactions ensure data consistency
    // and integrity by grouping multiple operations into a single unit of work,
    // allowing for rollback in case of an error.

    //Error: Transactional methods are only allowed on a replica set member or mongos' on
    //server localhost:27017

    //Note: The mongodb that we are using is only one instance that is running on localhost:27017
    //and there is no replication here. Replication is mandatory for Transaction to happen.
    //So now we use MongoDB Atlas so that our data will be managed
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName)
    {
        try{
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            //user.setUserName(null);
            userService.saveUser(user);
        }catch(Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("An error occured while saving an entry");
        }


    }

    public void saveEntry(JournalEntry journalEntry)
    {
       journalEntryRepository.save(journalEntry);


    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();

    }

//    public List<JournalEntry> getEntryById(ObjectId myId){
//        return journalEntryRepository.findAllById(Collections.singleton(myId));
//
//    }

    public Optional<JournalEntry> getEntryById(ObjectId myId){
        return journalEntryRepository.findById(myId);

    }


    //in below delete method we are first removing the JournalEntry from the users collection
    //using the Id of that JournalEntry, and then deleting it from journal_entries collection.
    //because each JournalEntry will be of specific user, so simply deleting the JournalEntry
    //from journal_entries will not delete it from users collection.

    @Transactional
    public boolean deleteById(ObjectId myId, String userName) {
        boolean removed = false;
        try{
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x->x.getId().equals(myId));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(myId);
            }

        }
        catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry",e);
        }
        return removed;

    }

    public List<JournalEntry> findByUserName(String username){
        return null;
    }
}
