package repositories;

import models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ApplicationRepository extends JpaRepository<ApplicationUser,Long> {


    ApplicationUser findByUsername(String username);
}
