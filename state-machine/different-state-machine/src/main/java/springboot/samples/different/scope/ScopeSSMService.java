package springboot.samples.different.scope;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author gentjan kolicaj
 * @since 4/6/26 3:10 PM
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ScopeSSMService {

    private final ScopeSSMConfig scopeSSMConfig;

    private final Map<String, StateMachine<ScopeSSMState, ScopeSSMEvent>> ssmMap = new ConcurrentHashMap<>();

    public StateMachine<ScopeSSMState, ScopeSSMEvent> getStateMachine(String sessionId) throws Exception {
        if (sessionId == null) {
            throw new IllegalStateException("Invalid session-id");
        }
        StateMachine<ScopeSSMState, ScopeSSMEvent> ssm = ssmMap.get(sessionId);
        if (ssm == null) {
            ssm = scopeSSMConfig.createStateMachine();
            log.info("Create ScopeSSM '{}'", ssm);
            ssmMap.put(sessionId, ssm);
            log.info("Started ScopeSSM '{}'...", ssm);
        }
        return ssm;
    }


}
