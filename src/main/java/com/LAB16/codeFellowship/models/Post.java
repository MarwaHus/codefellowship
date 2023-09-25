package com.LAB16.codeFellowship.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String body;
    private LocalDate createdAt;

    @ManyToOne
    private ApplicationUser applicationUser;


    public Post(String body, ApplicationUser appUser) {

    }



    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public ApplicationUser getAppUser() {
        return applicationUser;
    }

    public void setAppUser(ApplicationUser appUser) {
        this.applicationUser = appUser;
    }

}