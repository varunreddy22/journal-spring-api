package com.varun.myJournal.controller;

import com.varun.myJournal.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

//    Map<Long, JournalEntry> journalEntries = new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAll()
//    {
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @GetMapping("id/{myId}")
//    public JournalEntry getJournalEntryById(@PathVariable Long myId)
//    {
//        return journalEntries.get(myId);
//
//    }
//
//    @PostMapping()
//    public boolean createEntry(@RequestBody JournalEntry myEntry)
//    {
//
//        journalEntries.put(myEntry.getId(), myEntry);
//        return true;
//
//    }
//
//    @DeleteMapping("/id/{myId}")
//    public boolean deleteEntryById(@PathVariable Long myId)
//    {
//        journalEntries.remove(myId);
//        return true;
//    }
//
//    @PutMapping("/id/{" +
//            "myId}")
//    public JournalEntry updateEntryById(@PathVariable Long myId, @RequestBody JournalEntry newEntry)
//    {
//        return journalEntries.put(myId, newEntry);
//    }
//
//
//

}
