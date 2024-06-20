package springboot.samples.jwt01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.jwt01.domain.News;

/**
 * @author gentjan kolicaj
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}
