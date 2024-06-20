package springboot.samples.jwt01.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.samples.jwt01.repository.NewsRepository;
import springboot.samples.jwt01.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

  private static final Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

  private final NewsRepository newsRepository;

  @Autowired
  public NewsServiceImpl(NewsRepository newsRepository) {
    this.newsRepository = newsRepository;
  }
}
