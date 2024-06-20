package springboot.samples.jwt02.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.samples.jwt02.domain.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {

  Optional<AppUser> findByUsername(String username);

}
