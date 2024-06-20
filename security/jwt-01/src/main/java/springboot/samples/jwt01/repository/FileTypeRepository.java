package springboot.samples.jwt01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.samples.jwt01.domain.FileType;

/**
 * @author gentjan kolicaj
 */
@Repository
public interface FileTypeRepository extends JpaRepository<FileType, Integer> {

}
