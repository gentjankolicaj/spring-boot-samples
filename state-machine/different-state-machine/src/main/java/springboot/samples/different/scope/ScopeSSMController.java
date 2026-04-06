package springboot.samples.different.scope;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gentjan kolicaj
 * @since 4/6/26 2:58 PM
 *
 */
@Slf4j
@RestController
@RequestMapping("statemachine/scope")
public class ScopeSSMController {

  private final ScopeSSMService scopeSSMService;

  public ScopeSSMController(ScopeSSMService scopeSSMService) {
    this.scopeSSMService = scopeSSMService;
  }

  @PostMapping
  public void event(HttpSession httpSession, @RequestBody ScopeSSMEvent event) throws Exception {
    StateMachine<ScopeSSMState, ScopeSSMEvent> sessionSSM = scopeSSMService.getStateMachine(
        httpSession.getId());
    sessionSSM.sendEvent(event);
    log.info("ScopeSSM '{}' state:{}", sessionSSM, sessionSSM.getState());
  }
}
