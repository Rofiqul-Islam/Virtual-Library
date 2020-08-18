package com.project.controller;

import com.project.model.AuthenticationCredentials;
import com.project.model.Person;
import com.project.repository.AuthenticationCredentialsRepository;
import com.project.repository.PersonRepository;
import com.project.service.LoginCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.lang.reflect.Member;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AuthenticationCredentialsRepository authenticationCredentialsRepository;

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/all")
    public Iterable<Person> findAll(){
        System.out.println("reached here");
        return personRepository.findAll();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Integer authenticationViaEmail(@RequestBody LoginCredential loginCredential) {
        try{
            int id = personRepository.findWithLoginCredential(loginCredential.getEmail(),loginCredential.getPassword());
            Random rand = new Random();
            String authCode = String.format("%04d", rand.nextInt(10000));
            sendMail(loginCredential.getEmail(), authCode);
            authenticationCredentialsRepository.save(new AuthenticationCredentials(0, id,authCode));
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void sendMail(String recepient, String authenticationCode) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");


        String myAccountEmail = "virutallibrary2020@gmail.com";
        String password = "sh271095";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });
        Message message = prepareMessage(session,myAccountEmail,recepient,authenticationCode);
        Transport.send(message);
        System.out.println("Email has sent succesfully");

    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String authenticationCode){
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Authentication Email from Virtual Library");
            message.setText("Hi, \nYour confirmation code is "+authenticationCode+".");
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
