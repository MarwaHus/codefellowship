
package com.LAB16.codeFellowship.repositories;


import com.LAB16.codeFellowship.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<ApplicationUser,Long> {


    ApplicationUser findByUsername(String username);
}
