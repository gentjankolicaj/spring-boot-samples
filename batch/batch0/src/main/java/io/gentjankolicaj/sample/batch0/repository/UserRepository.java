package io.gentjankolicaj.sample.batch0.repository;

import io.gentjankolicaj.sample.batch0.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
