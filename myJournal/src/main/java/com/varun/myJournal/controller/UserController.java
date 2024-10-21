package com.varun.myJournal.controller;

import com.varun.myJournal.api.response.WeatherResponse;
import com.varun.myJournal.entity.User;
import com.varun.myJournal.repository.UserRepository;
import com.varun.myJournal.service.UserService;
import com.varun.myJournal.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        //we fetch the above userName while authorizing the user, by giving the
        //username and password in (Basic Auth i.e in POSTMAN)
        //So while authorization is done and the user is authorized, we save the
        //userName in a String. Instead of getting it as a Parameter in @PutMapping.
        System.out.println("Authenticated username: " + userName);
        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("MUMBAI");
        String greetings = "";
        if (weatherResponse != null) {
            greetings = "Hi " + authentication.getName() + "," + " Weather feels like " + weatherResponse.getCurrent().getFeelslike();

        }
        return new ResponseEntity<>(greetings, HttpStatus.OK);

    }
}















