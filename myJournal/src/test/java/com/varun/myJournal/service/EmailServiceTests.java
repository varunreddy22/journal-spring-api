package com.varun.myJournal.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail(){
        emailService.sendEmail("abcde@gmail.com","Greetings","Hi, How are you?" );
    }
}
