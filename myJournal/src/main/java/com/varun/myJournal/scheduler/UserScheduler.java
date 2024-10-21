package com.varun.myJournal.scheduler;


import com.varun.myJournal.cache.AppCache;
import com.varun.myJournal.entity.JournalEntry;
import com.varun.myJournal.entity.User;
import com.varun.myJournal.repository.UserRepositoryImpl;
import com.varun.myJournal.service.EmailService;
import com.varun.myJournal.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.tomcat.util.buf.StringUtils.join;

@Component
public class UserScheduler {


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;



    @Scheduled(cron = "0 0 9 * * SUN")
//    @Scheduled(cron = "0 * * ? * *")
    public void fetchUsersAndSendSaMail(){
        List<User> users = userRepositoryImpl.getUserForSA();
        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ", filteredEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", sentiment);


        }

    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }


}

//The above Scheduled method will send the email to the above fetched user and
//about their sentiment or behaviour( based on their journalEntry content using ML)
//every Sunday at 9:00 am


//Remember we created an AppCache class to initialize the api for weather.
//we can do that by using this @Scheduled.
//Here we have scheduled the appCache to clear for every 10 minutes.

