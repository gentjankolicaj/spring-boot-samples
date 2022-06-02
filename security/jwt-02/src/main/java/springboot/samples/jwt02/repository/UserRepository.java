package springboot.samples.jwt02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.samples.jwt02.domain.AppUser;

public interface UserRepository extends JpaRepository<AppUser,Long> {

    AppUser findByUsername(String username);

}
