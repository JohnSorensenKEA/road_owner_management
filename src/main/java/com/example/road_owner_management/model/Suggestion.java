package com.example.road_owner_management.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "suggestions")
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String author;
    private String suggestion;
    private Date date;

    public Suggestion(){

    }

    public Suggestion(String author, String suggestion) {
        this.author = author;
        this.suggestion = suggestion;
        this.date = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Medlem='" + author + '\'' +
                ", Forslag='" + suggestion + '\'' +
                '}';
    }
}
