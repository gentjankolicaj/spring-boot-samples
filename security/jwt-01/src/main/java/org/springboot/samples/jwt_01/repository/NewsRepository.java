package org.springboot.samples.jwt_01.repository;

import org.springboot.samples.jwt_01.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * 
 * @author gentjan kolicaj
 *
 */
@Repository
public interface NewsRepository extends JpaRepository<News,Long>{

}
