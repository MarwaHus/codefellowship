
package com.LAB16.codeFellowship.config;

import com.LAB16.codeFellowship.repositories.ApplicationRepository;
import com.LAB16.codeFellowship.models.ApplicationUser;
package  com.LAB16.codeFellowship.config;

import com.LAB16.codeFellowship.models.ApplicationUser;
import com.LAB16.codeFellowship.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationRepository.findByUsername(username);
        if (applicationUser == null) {
            System.out.println("User not found " + username);
            throw new UsernameNotFoundException("user" + username + " was not found in the db");
        }
        System.out.println("Found User: " + applicationUser.getUsername());
        return applicationUser;
    }
}