package org.app.service.impl;

import org.app.repository.FileTypeRepository;
import org.app.service.FileTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileTypeServiceImpl implements FileTypeService{

	private static final Logger logger= LoggerFactory.getLogger(FileTypeServiceImpl.class);
	
	@Autowired
	private FileTypeRepository fileTypeRepository;
	
}
