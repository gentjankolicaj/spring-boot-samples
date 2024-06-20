package springboot.samples.jwt01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springboot.samples.jwt01.domain.Country;

@Controller
@RequestMapping(CountryController.COUNTRY_URI)
public class CountryController {

  protected static final String COUNTRY_URI = "/country";

  private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

  @RequestMapping(path = {"", "/", "/*"}, method = RequestMethod.GET)
  public Country getCountry() {
    return new Country("Albania", "355", "AL", "ALB");
  }

}
