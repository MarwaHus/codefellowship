
package  com.LAB16.codeFellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String bio;
    private LocalDate createdDate;
    @OneToMany(mappedBy = "appUser")
    private List<Post> userPosts;
    @ManyToMany
    @JoinTable(
            name="user_followers",
            joinColumns = { @JoinColumn(name = "primaryUser") },
            inverseJoinColumns = { @JoinColumn(name = "followedUser") }
    )
    Set<ApplicationUser> followers;

    @ManyToMany(mappedBy = "followers")
    Set<ApplicationUser> usersFollowedBy;

    public ApplicationUser(String username, String password, String firstName, String lastName, Date dateOfBirth, String bio, LocalDate createdDate) {
=======

    public ApplicationUser(){

    }

    public ApplicationUser(String username, String password, String firstName, String lastName, Date dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.createdDate=createdDate;
    }

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void addFollower(ApplicationUser follower) {
        followers.add(follower);
    }

    public void removeFollower(ApplicationUser follower) {
        followers.remove(follower);
    }

    public Set<ApplicationUser> getFollowers() {
        return followers;
    }
}
