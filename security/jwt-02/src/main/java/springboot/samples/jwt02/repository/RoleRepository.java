package springboot.samples.jwt02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.samples.jwt02.domain.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Long> {

  AppRole findByName(String name);

}
