package springboot.samples.different.cdplayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statemachine/cdplayer")
public class CDPlayerController {


  private final CDPlayerService cdPlayerService;

  @Autowired
  public CDPlayerController(CDPlayerService cdPlayerService) {
    this.cdPlayerService = cdPlayerService;
  }


  @PostMapping
  public void sendEvent(Events events) {
    cdPlayerService.sendEvent(events);
  }

}
