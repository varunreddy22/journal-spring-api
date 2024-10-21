package com.varun.myJournal.controller;

import com.varun.myJournal.entity.User;
import com.varun.myJournal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll()
    {
        return userService.getAll();
    }


    @PostMapping
    public boolean createUser(@RequestBody User newUser) {
        userService.saveNewUser(newUser);
        return true;
    }
}
