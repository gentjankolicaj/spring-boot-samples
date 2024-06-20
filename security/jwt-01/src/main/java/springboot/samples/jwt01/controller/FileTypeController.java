package springboot.samples.jwt01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springboot.samples.jwt01.domain.FileType;

@Controller
@RequestMapping(FileTypeController.FILE_TYPE_URI)
public class FileTypeController {

  protected static final String FILE_TYPE_URI = "/file_type";

  private static final Logger logger = LoggerFactory.getLogger(FileTypeController.class);

  @RequestMapping(path = {"", "/", "/*"}, method = RequestMethod.GET)
  public FileType getFileType() {
    return new FileType(Integer.valueOf(1), "text", ".txt");
  }
}
