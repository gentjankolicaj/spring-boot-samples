package springboot.samples.integration.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.samples.integration.kafka.oca.OCAGateway;
import springboot.samples.integration.kafka.rxtx.RxTxGateway;

@Slf4j
@RestController
@RequestMapping("/messages")
public class MessageController {

  @Autowired
  OCAGateway ocaGateway;

  @Autowired
  RxTxGateway rxTxGateway;

  @PostMapping("/oca")
  public void outboundChannelAdapter(@RequestBody Greet greet) {
    ocaGateway.push(greet);
    log.info("oca pushed message {}", greet);
  }

  @PostMapping("/rxtx")
  public String rxtx(@RequestBody Greet greet) {
    return rxTxGateway.pushAndPool(greet);
  }

}
