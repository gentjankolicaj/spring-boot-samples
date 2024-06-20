package springboot.samples.jwt01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springboot.samples.jwt01.domain.User;

@Controller
@RequestMapping(UserController.USER_URI)
public class UserController {

  protected static final String USER_URI = "/user";

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @RequestMapping(path = {"", "/", "/*"}, method = RequestMethod.GET)
  public User getUser() {
    return new User("jkal1231123", "john", "doe", "johndoe@gmail.com", Integer.valueOf(12312321));
  }

}
