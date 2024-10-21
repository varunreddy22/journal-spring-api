package com.varun.myJournal.controller;

import com.varun.myJournal.entity.JournalEntry;
import com.varun.myJournal.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal1")
public class JournalEntryController1 {

    @Autowired
    private JournalEntryService journalEntryService;
    // Using Autowired annotation we are injecting the JournalEntryService
    // instance that is created by Spring--this is called dependency injection


    @GetMapping
    public ResponseEntity<?> getAll()
    {
        //return journalEntryService.getAll();
        List<JournalEntry> all = journalEntryService.getAll();
        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId)
    {
        Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(myId);
        if(journalEntry.isPresent())
        {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping()
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry)
    {
        try{
            //myEntry.setDate(LocalDateTime.now());
            //journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId)
    {
        //journalEntryService.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry)
    {

        JournalEntry old = journalEntryService.getEntryById(myId).orElse(null);
        if(old!=null)
        {
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals(" ") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals(" ") ? newEntry.getContent() : old.getContent());
            //journalEntryService.saveEntry(old, user);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        //newEntry.setDate(LocalDateTime.now());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }




}
