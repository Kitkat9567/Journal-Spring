package com.example.demo.contoller;

import com.example.demo.entity.Journal;
import com.example.demo.entity.User;
import com.example.demo.service.JournalEntryService;
import com.example.demo.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserEntityController {

    @Autowired
    public UserEntryService userEntryService;


    @GetMapping("/getUsers")
    public List<User> getAll(){
        return userEntryService.getUser();
    }

    @PostMapping("/setUsers")
    public ResponseEntity<Object> createEntry(@RequestBody User entry){
        try{
            userEntryService.saveUser(entry);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUser/id/{id}")
    public ResponseEntity<User> getById(@PathVariable ObjectId id){
        Optional<User> userEntry =  userEntryService.getUserbyId(id);
        if(userEntry.isPresent()){
            return new ResponseEntity<>(userEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteUser/id/{id}")
    public String deleteById(@PathVariable ObjectId id){
        String userId = userEntryService.getUserbyId(id).get().getUserName();
        userEntryService.deleteUser(id);
        return userId;
    }




//    @PutMapping("/putValue/id/{id}")
//    public Journal putById(@PathVariable ObjectId id,@RequestBody Journal newEntry){
//        Journal old = journalEntryService.getJournalbyId(id).orElse(null);
//        if(old != null){
//            old.setHeader(((newEntry.getHeader() != null) || newEntry.getHeader().equals(" ")) ? newEntry.getHeader() : old.getHeader());
//            old.setContent(newEntry.getContent() != null ? newEntry.getContent(): old.getContent());
//        }
//        journalEntryService.saveJournal(old);
//        return journalEntryService.getJournalbyId(id).orElse(null);
//    }
}
