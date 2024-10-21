package com.varun.myJournal.service;

import com.varun.myJournal.entity.User;
import com.varun.myJournal.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @ParameterizedTest
//    @CsvSource({
//            "Ram",
//            "Varun",
//            "Saurabh"
//    })
    @ValueSource(strings = {
            "Ram",
           "Varun",
           "Saurabh"
    })
    public void testFindName(String name) {
        //assertEquals(4,2+0);
        assertNotNull(userRepository.findByUserName(name), "failed for"+name);
        //User user = userRepository.findByUserName("Ram");
        //assertNotNull(!user.getJournalEntries().isEmpty());
    }


        @ParameterizedTest
                @CsvSource({
                        "1,1,2",
                        "2,2,4",
                        "3,3,6",
                        "4,5,8"

                })
        public void testAdd(int a, int b, int expected){
            assertEquals(expected, a+b);

        }

        @ParameterizedTest
        //we can also make our custom source(i.e custom parameters) using @ArgumentsSource)
        @ArgumentsSource(UserArgumentsProvider.class)
        public void testSaveNewUser(User name) {

            assertTrue(userService.saveNewUser(name));

        }

//        @BeforeAll
//    @BeforeEach
//    @AfterAll
//    @AfterEach


    //Note: The Coverage plugin in IntelliJ IDEA is a built-in tool that helps
    // you measure and visualize how much of your code is exercised when you run
    // tests. It shows which parts of your code are covered (executed) during tests
    // and which parts are not, allowing you to see how thoroughly your tests check
    // your code.




}
