<<<<<<< HEAD
package com.LAB16.codeFellowship.controller;

import com.LAB16.codeFellowship.models.Post;
import com.LAB16.codeFellowship.repositories.ApplicationRepository;

import com.LAB16.codeFellowship.models.ApplicationUser;
import com.LAB16.codeFellowship.repositories.PostRepository;
=======
package  com.LAB16.codeFellowship.controller;

import com.LAB16.codeFellowship.models.ApplicationUser;
import com.LAB16.codeFellowship.repositories.ApplicationRepository;
>>>>>>> a83c82c06b907115e67456009a886ff797797fb8
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractAuditable_.createdDate;
import java.util.Date;


@Controller
public class HomeController {

    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    PostRepository postRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup.html";
    }

    @GetMapping("/")
    public String getHomePage(Principal p, Model m) {
        try {
            if (p != null) {
                String username = p.getName();
                ApplicationUser applicationUser = applicationRepository.findByUsername(username);
                m.addAttribute("username", username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "home.html";
    }


    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String firstName, String lastName, @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth, String bio, LocalDate createdDate) {
        String encryptedPassword = passwordEncoder.encode(password);
        ApplicationUser appUser = new ApplicationUser(username, encryptedPassword, firstName, lastName, dateOfBirth, bio,createdDate);
    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String firstName, String lastName, @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth, String bio) {
        String encryptedPassword = passwordEncoder.encode(password);
        ApplicationUser appUser = new ApplicationUser(username, encryptedPassword, firstName, lastName, dateOfBirth, bio);
        applicationRepository.save(appUser);
        authWithHttpServletRequest(username, password);
        Authentication authentication = new UsernamePasswordAuthenticationToken(appUser, null, appUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @GetMapping("/logout")
    public String getLogoutPage() {
        return "home.html";
    }
    @GetMapping("/myprofile")
    public String getMyProfile(Principal p, Model model){
        ApplicationUser applicationUser= applicationRepository.findByUsername(p.getName());
        model.addAttribute("users",applicationUser);
        List<Post> posts=postRepository.findAllByAppUser(applicationUser);
        model.addAttribute("posts",posts);
        return "userprofile";

    }
    @GetMapping("/users/{id}")
    public String getUserInfo(Model m, Principal p, @PathVariable Long id)
    {
        if (p != null)
        {
            String username = p.getName();
            ApplicationUser applicationUser = applicationRepository.findByUsername(username);
            m.addAttribute("username", username);

        }

        ApplicationUser applicationUser = applicationRepository.findById(id).orElseThrow();
        m.addAttribute("Username", applicationUser.getUsername());
        m.addAttribute("UserId", applicationUser.getId());
        return "/profile.html";
    }

    @PostMapping("/user/follow")
    public RedirectView addFollower(int followedUser, Principal p) {
        ApplicationUser anotherUser = applicationRepository.findByUsername(p.getName());
        anotherUser.addFollower(applicationRepository.findById((long) followedUser).get());
        applicationRepository.save(anotherUser);
        return new RedirectView("/follow");
    }

    @PostMapping("/user/unfollow")
    public RedirectView removeFollower(int unfollowedUser, Principal p) {
        ApplicationUser anotherUser = applicationRepository.findByUsername(p.getName());
        anotherUser.removeFollower(applicationRepository.findById((long) unfollowedUser).get());
        applicationRepository.save(anotherUser);
        return new RedirectView("/follow");
    }
    @GetMapping("/feed")
    public String getFeed(Principal p, Model m) {
        ApplicationUser appUser = applicationRepository.findByUsername(p.getName());
        m.addAttribute("appUser", appUser);
        return "feed";
    }
    private void authWithHttpServletRequest(String username, String password) {
    @GetMapping("/")
    public String getHomePage(Principal p, Model m){
        if(p != null){
            String username = p.getName();
            ApplicationUser appUser= applicationRepository.findByUsername(username);
            m.addAttribute("username", username);
        }
        return "home.html";
    }
    public void authWithHttpServletRequest(String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}