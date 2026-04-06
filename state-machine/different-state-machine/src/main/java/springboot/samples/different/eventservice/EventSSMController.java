package springboot.samples.different.eventservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statemachine/event")
@RequiredArgsConstructor
public class EventSSMController {

  private final EventSSMService eventSSMService;


  @PostMapping
  public void event(EventSSMEvent eventSSMEvent) {
    this.eventSSMService.event(eventSSMEvent);
  }


}
