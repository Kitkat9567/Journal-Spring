package com.example.demo.service;

import com.example.demo.entity.Journal;
import com.example.demo.entity.User;
import com.example.demo.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {

    @Autowired
    public UserEntryRepository userEntryRepository;


    public void saveUser(User user){
        userEntryRepository.save(user);
    }

    public List<User> getUser(){
       return userEntryRepository.findAll();
    }

    public Optional<User> getUserbyId(ObjectId id){
        return userEntryRepository.findById(id);
    }

    public void deleteUser(ObjectId id){
        userEntryRepository.deleteById(id);
    }

    public User findbyUsername(String userName){
       return userEntryRepository.findByuserName(userName);
    }


}
