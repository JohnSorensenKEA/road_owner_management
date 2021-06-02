package com.example.road_owner_management.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailService {

    private Message msg;

    private String message;
    private String subject;
    private String recipient;

    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public SendEmailService(String subject, String message) {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        final String username = "vejejerlauget.emails@gmail.com";//
        final String password = "vuodllrmicppkjbk";
        try {
            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            // -- Create a new message --
            Message msg = new MimeMessage(session);
            // -- Set the FROM field --
            msg.setFrom(new InternetAddress("vejejerlauget.emails@gmail.com"));
            this.msg = msg;
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
        setSubject(subject);
        setMessage(message);
    }

    public void setMessage(String message){
        try {
            msg.setText(message + "\n\n\n Denne mail kan ikke besvares.");
        }catch(Exception e){
            System.out.println("Error: " + e);
        }

    }

    public void setSubject(String subject){
        try {
            msg.setSubject(subject);
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    public void addRecipient(String recipient){
        try {
            msg.addRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    public void addRecipient(String[] recipients){
        try{
            for(int i = 0; i < recipients.length; i++){
                msg.addRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(recipients[i]));
            }

        }catch(Exception e){
            System.out.println("Error: " + e);
        }

    }

    public boolean send(){
        try {
            Transport.send(msg);
            return true;
        }catch(Exception e){
            System.out.println("Error: " + e);
            return false;
        }
    }
}
