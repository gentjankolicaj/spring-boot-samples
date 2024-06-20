package springboot.samples.jwt01.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.samples.jwt01.repository.FileTypeRepository;
import springboot.samples.jwt01.service.FileTypeService;

@Service
public class FileTypeServiceImpl implements FileTypeService {

  private static final Logger logger = LoggerFactory.getLogger(FileTypeServiceImpl.class);

  @Autowired
  private FileTypeRepository fileTypeRepository;

}
