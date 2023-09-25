package com.LAB16.codeFellowship.controller;


import com.LAB16.codeFellowship.models.ApplicationUser;
import com.LAB16.codeFellowship.models.Post;
import com.LAB16.codeFellowship.repositories.ApplicationRepository;
import com.LAB16.codeFellowship.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class PostController {
        @Autowired
        ApplicationRepository applicationRepository;

        @Autowired
        PostRepository postRepository;

        @GetMapping("/post")
        public String getPost(Principal p, Model m) {
            ApplicationUser appUser = applicationRepository.findByUsername(p.getName());
            List<Post> userPosts=postRepository.findAllByAppUser(appUser);
            m.addAttribute("posts", userPosts);
            return "profile";
        }

        @PostMapping("/post")
        public RedirectView createPost(String body, Principal p) {
            ApplicationUser appUser = applicationRepository.findByUsername(p.getName());
            Post post = new Post(body, appUser);
            postRepository.save(post);
            return new RedirectView("/post");
        }
    }

