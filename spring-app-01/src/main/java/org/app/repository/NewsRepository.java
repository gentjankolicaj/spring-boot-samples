package org.app.repository;

import org.app.model.News;
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
