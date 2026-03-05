package springboot.samples.integration.kafka;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {


  @RequestMapping("/outboundChannelAdapter")
  public void outboundChannelAdapter(@RequestBody Greet greet) {

  }

}
