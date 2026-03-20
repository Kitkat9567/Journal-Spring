package com.example.demo.contoller;

import com.example.demo.entity.Journal;
import com.example.demo.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntityController {

    @Autowired
    public JournalEntryService journalEntryService;



    @GetMapping("/getValues/{userName}")
    public List<Journal> getAll(@PathVariable String userName){
        return journalEntryService.getJournal(userName);
    }

    @PostMapping("/setValues/{userName}")
    public ResponseEntity<Journal> createEntry(@RequestBody Journal entry, @PathVariable String userName){
        try{
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveJournal(entry, userName);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getValue/id/{id}")
    public ResponseEntity<Journal> getById(@PathVariable ObjectId id){
        Optional<Journal> journalEntry =  journalEntryService.getJournalbyId(id);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteValue/id/{id}")
    public boolean deleteById(@PathVariable ObjectId id){
        journalEntryService.deleteJournal(id);
        return true;
    }

    @DeleteMapping("/deleteJournal/{id}/{userName}")
    public void deleteUserJournal(@PathVariable String userName, @PathVariable ObjectId id){
        journalEntryService.deleteUserJournal(userName,id);
    }

//    @PutMapping("/putValue/id/{id}")
//    public Journal putById(@PathVariable ObjectId id,@RequestBody Journal newEntry){
//        Journal old = journalEntryService.getJournalbyId(id).orElse(null);
//        if(old != null){
//            old.setHeader(((newEntry.getHeader() != null) || newEntry.getHeader().equals(" ")) ? newEntry.getHeader() : old.getHeader());
//            old.setContent(newEntry.getContent() != null ? newEntry.getContent(): old.getContent());
//        }
//        journalEntryService.saveJournal(old, userName);
//        return journalEntryService.getJournalbyId(id).orElse(null);
//    }
}
