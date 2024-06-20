package springboot.samples.jwt01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springboot.samples.jwt01.domain.News;

@Controller
@RequestMapping(NewsController.NEWS_URI)
public class NewsController {

  protected final static String NEWS_URI = "/news";

  private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

  @RequestMapping(path = {"", "/", "/*"}, method = RequestMethod.GET)
  public News getNews() {
    return new News(Long.valueOf(1), "News title", "News subject", "News content");
  }

}
