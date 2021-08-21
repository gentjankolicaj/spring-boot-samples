package org.springboot.samples.jwt_01.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.samples.jwt_01.repository.NewsRepository;
import org.springboot.samples.jwt_01.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

	private static final Logger logger= LoggerFactory.getLogger(NewsServiceImpl.class);

	private final NewsRepository newsRepository;

	@Autowired
	public NewsServiceImpl(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}
}
