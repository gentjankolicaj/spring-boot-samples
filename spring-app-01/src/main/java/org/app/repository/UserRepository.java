package org.app.repository;

import org.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * 
 * @author gentjan kolicaj
 *
 */
@Repository
public interface UserRepository  extends JpaRepository<User,String>{

}
