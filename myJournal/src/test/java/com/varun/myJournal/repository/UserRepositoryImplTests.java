package com.varun.myJournal.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTests {


    @Autowired
    private UserRepositoryImpl userRepositoryimpl;

    @Test
    public void testUser(){

            Assertions.assertNotNull(userRepositoryimpl.getUserForSA());

    }
}
