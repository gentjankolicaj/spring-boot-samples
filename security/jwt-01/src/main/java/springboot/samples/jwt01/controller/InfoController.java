package springboot.samples.jwt01.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(InfoController.INFO_URI)
public class InfoController {

  protected static final String INFO_URI = "/info";

  @Autowired
  private ApplicationContext applicationContext;


  @RequestMapping(path = {"", "/", "/*"})
  private List<String> getInfo() {
    String[] beanNames = applicationContext.getBeanDefinitionNames();
    List<String> tmpList = Arrays.asList(beanNames);
    return tmpList;
  }

}
