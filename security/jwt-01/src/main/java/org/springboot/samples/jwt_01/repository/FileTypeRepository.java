package org.springboot.samples.jwt_01.repository;

import org.springboot.samples.jwt_01.model.FileType;
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
