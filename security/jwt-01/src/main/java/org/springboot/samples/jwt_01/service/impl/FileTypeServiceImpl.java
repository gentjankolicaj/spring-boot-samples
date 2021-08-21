package org.springboot.samples.jwt_01.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.samples.jwt_01.repository.FileTypeRepository;
import org.springboot.samples.jwt_01.service.FileTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileTypeServiceImpl implements FileTypeService{

	private static final Logger logger= LoggerFactory.getLogger(FileTypeServiceImpl.class);
	
	@Autowired
	private FileTypeRepository fileTypeRepository;
	
}
