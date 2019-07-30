package org.app.service.impl;

import org.app.Application;
import org.app.repository.NewsRepository;
import org.app.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

	private static final Logger logger= LoggerFactory.getLogger(NewsServiceImpl.class);
	
	@Autowired
	private NewsRepository newsRepository;
	
	

}
