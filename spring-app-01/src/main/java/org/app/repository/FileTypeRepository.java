package org.app.repository;

import org.app.model.FileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gentjan kolicaj
 *
 */
@Repository
public interface FileTypeRepository extends JpaRepository<FileType,Integer>{

}
