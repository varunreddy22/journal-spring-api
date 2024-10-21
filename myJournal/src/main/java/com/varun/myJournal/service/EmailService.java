package com.varun.myJournal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body){
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            javaMailSender.send(mail);


        }catch(Exception e){
            log.error("Exception while sendEmail",e);

        }
    }
}



//Spring provides an interface called "JavaMailSender", using which we can send mails
//so we add a dependency "spring-boot-starter-mail" in pom.xml
//with the help of this "spring-boot-starter-mail" dependency, using JavaMailSender we can send mails from our spring boot application.