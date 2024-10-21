package com.varun.myJournal.service;

import com.varun.myJournal.entity.User;
import com.varun.myJournal.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@SpringBootTest
public class UserDetailsServiceImplTests {


    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private UserRepository userRepository;


    @Test
    public void testLoadUserByUserName(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("abcdef").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }


}
