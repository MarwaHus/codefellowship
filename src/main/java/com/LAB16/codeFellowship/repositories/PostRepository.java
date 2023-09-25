package com.LAB16.codeFellowship.repositories;

import com.LAB16.codeFellowship.models.ApplicationUser;
import com.LAB16.codeFellowship.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findAllByAppUser(ApplicationUser appUser);
}
