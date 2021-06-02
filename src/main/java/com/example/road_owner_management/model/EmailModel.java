package com.example.road_owner_management.model;


public class EmailModel {

    private String subject;
    private String message;
    private String[] recipients;



    public EmailModel(String subject, String message, String[] recipients) {
        this.subject = subject;
        this.message = message;
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }
}
