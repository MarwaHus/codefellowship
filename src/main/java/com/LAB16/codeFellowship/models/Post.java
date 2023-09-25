
 package  com.LAB16.codeFellowship.models;

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


    public Post() {

    }

    public Post(String body, ApplicationUser applicationUser) {
        this.body = body;
        this.applicationUser = applicationUser;
    }

    public int getId() {
        return id;
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

<<<<<<< HEAD
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
    }

    public ApplicationUser getAppUser() {
        return applicationUser;
    }

    public void setAppUser(ApplicationUser appUser) {
        this.applicationUser = appUser;
    }

}


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", createdAt=" + createdAt +
                ",applicationUser =" + applicationUser +
                '}';
    }
}


