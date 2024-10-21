package com.varun.myJournal.service;

import com.varun.myJournal.entity.User;
import com.varun.myJournal.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@Component
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public boolean saveNewUser(User user){
        try{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        return true;
           } catch(Exception e){
                return false;
        }
    }


    public void saveUser(User user)
    {
        userRepository.save(user);
    }

    public void saveAdmin(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }


    public Optional<User> getEntryById(ObjectId myId){
        return userRepository.findById(myId);

    }

    public void deleteById(ObjectId myId) {

        userRepository.deleteById(myId);
    }

    public User findByUserName(String username)
    {
        return userRepository.findByUserName(username);
    }



}




//    public List<JournalEntry> getEntryById(ObjectId myId){
//        return journalEntryRepository.findAllById(Collections.singleton(myId));
//
//    }
