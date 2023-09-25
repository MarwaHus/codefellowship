package  com.LAB16.codeFellowship.controller;

import com.LAB16.codeFellowship.models.ApplicationUser;
import com.LAB16.codeFellowship.repositories.ApplicationRepository;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;


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