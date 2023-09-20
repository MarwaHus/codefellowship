package repositories;

import models.ApplicationUser;
import models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findAllByAppUser(ApplicationUser applicationUser);
}