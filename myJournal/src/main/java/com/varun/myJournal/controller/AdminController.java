package com.varun.myJournal.controller;

import com.varun.myJournal.cache.AppCache;
import com.varun.myJournal.entity.User;
import com.varun.myJournal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userService.getAll();

        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }

        return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);

        //user.stream().map(User::getUserName).collect(Collectors.toSet());

    }

    @PostMapping("/create-admin-user")
    public void createAdmin(@RequestBody User user){
        userService.saveAdmin(user);
    }

    @GetMapping("/clear-app-cache")
    public void clearAppCache(){
        appCache.init();

    }
}
