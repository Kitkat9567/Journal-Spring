package com.example.demo.service;

import com.example.demo.entity.Journal;
import com.example.demo.entity.User;
import com.example.demo.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    public JournalEntryRepository journalEntryRepository;

    @Autowired
    public UserEntryService userEntryService;

    @Transactional
    public void saveJournal(Journal journal, String userName){
        User user = userEntryService.findbyUsername(userName);
        Journal saved = journalEntryRepository.save(journal);
        user.getJournalEntries().add(saved);
        userEntryService.saveUser(user);
    }

    public List<Journal> getJournal(String userName){
        User user = userEntryService.findbyUsername(userName);

       return user.getJournalEntries();
    }

    public Optional<Journal> getJournalbyId(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteJournal(ObjectId id){
        journalEntryRepository.deleteById(id);
    }

    public void deleteUserJournal(String userName, ObjectId id){
       User user = userEntryService.findbyUsername(userName);
       user.getJournalEntries().removeIf(x -> x.getId().equals(id));
       userEntryService.saveUser(user);
    }
}
