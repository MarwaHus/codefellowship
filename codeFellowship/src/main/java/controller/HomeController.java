package controller;

import models.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import repositories.ApplicationRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
public class HomeController {

    @Autowired
    ApplicationRepository applicationRepository;

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
    public RedirectView createUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        ApplicationUser applicationUser = new ApplicationUser(username, passwordEncoder.encode(password), firstName, lastName, dateOfBirth, bio);
        applicationRepository.save(applicationUser);
        authWithHttpServletRequest(username, password);
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
    @GetMapping("/post")
    public String getPost(Principal p, Model m) {
        ApplicationUser appUser = applicationRepository.findByUsername(p.getName());
        List<Post> userPosts=postRepository.findAllByAppUser(appUser);
        m.addAttribute("posts", userPosts);
        return "userprofile";
    }
    private void authWithHttpServletRequest(String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}