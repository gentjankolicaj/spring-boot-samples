package springboot.samples.integration.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.samples.integration.kafka.oca.OcaGateway;

@Slf4j
@RestController
@RequestMapping("/messages")
public class MessageController {

  @Autowired
  OcaGateway ocaGateway;

  @PostMapping("/outboundChannelAdapter")
  public void outboundChannelAdapter(@RequestBody Greet greet) {
    ocaGateway.push(greet);
    log.info("outboundChannelAdapter pushed message {}", greet);
  }

}
