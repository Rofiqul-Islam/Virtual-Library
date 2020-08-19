package com.project.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Util {

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
