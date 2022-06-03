package springboot.samples.jwt02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.samples.jwt02.domain.AppUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Long> {

    Optional<AppUser> findByUsername(String username);

}
