package springboot.samples.different.deploy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.guard.Guard;

/**
 *
 * @author gentjan kolicaj
 * @since 4/7/26 12:30 PM
 *
 */
@Slf4j
@Configuration
public class DeploySSMConfig {

    @Bean("deploySSM")
    public StateMachine<DeploySSMState, DeploySSMEvent> createStateMachine() throws Exception {
        StateMachineBuilder.Builder<DeploySSMState, DeploySSMEvent> builder = new StateMachineBuilder.Builder<>();

        //configure states
        builder.configureStates()
                .withStates()
                .initial(DeploySSMState.READY)
                .state(DeploySSMState.READY, readyEntryAction(), readyErrorAction())
                .state(DeploySSMState.DEPLOY)
                .state(DeploySSMState.UNDEPLOY)
                .state(DeploySSMState.ERROR, errorEntryAction())
                .and()

                //substates of deploy
                .withStates()
                .parent(DeploySSMState.DEPLOY)
                .initial(DeploySSMState.PREPARE_DEPLOY)
                .state(DeploySSMState.PREPARE_DEPLOY, ctx -> log.info("PREPARE_DEPLOY action."))
                .state(DeploySSMState.INSTALL)
                .state(DeploySSMState.START)
                .end(DeploySSMState.EXIT_DEPLOY)

                //substates of undeploy
                .and()
                .withStates()
                .parent(DeploySSMState.UNDEPLOY)
                .initial(DeploySSMState.PREPARE_UNDEPLOY)
                .state(DeploySSMState.PREPARE_UNDEPLOY)
                .state(DeploySSMState.STOP)
                .end(DeploySSMState.EXIT_UNDEPLOY);


        //configure transitions
        builder.configureTransitions()
                .withExternal().source(DeploySSMState.READY).target(DeploySSMState.DEPLOY).event(DeploySSMEvent.DEPLOY)
                .and()
                .withChoice()
                .source(DeploySSMState.PREPARE_DEPLOY)
                .first(DeploySSMState.START, isInstalledGuard())
                .last(DeploySSMState.INSTALL)
                .and()
                .withChoice()
                .source(DeploySSMState.INSTALL)
                .first(DeploySSMState.START, installedOkGuard())
                .last(DeploySSMState.EXIT_DEPLOY)
                .and()
                .withChoice()
                .source(DeploySSMState.EXIT_DEPLOY)
                .first(DeploySSMState.READY, hasErrorGuard())
                .last(DeploySSMState.ERROR)
                .and()
                .withExternal().source(DeploySSMState.READY).target(DeploySSMState.UNDEPLOY).event(DeploySSMEvent.UNDEPLOY)
                .and()
                .withChoice()
                .source(DeploySSMState.PREPARE_UNDEPLOY)
                .first(DeploySSMState.STOP, isRunningGuard())
                .last(DeploySSMState.EXIT_UNDEPLOY)
                .and()
                .withExternal()
                .source(DeploySSMState.STOP)
                .target(DeploySSMState.EXIT_UNDEPLOY);


        //configure configurations
        builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(true);

        return builder.build();
    }

    private Guard<DeploySSMState, DeploySSMEvent> isRunningGuard() {
        return ctx -> {
            log.info("isRunningGuard: ");
            return true;
        };
    }

    private Guard<DeploySSMState, DeploySSMEvent> hasErrorGuard() {
        return ctx -> {
            log.info("hasErrorGuard: ");
            return true;
        };
    }

    private Guard<DeploySSMState, DeploySSMEvent> isInstalledGuard() {
        return ctx -> {
            log.info("isInstalledGuard: ");
            return true;
        };
    }

    private Guard<DeploySSMState, DeploySSMEvent> installedOkGuard() {
        return ctx -> {
            log.info("installedOkGuard: ");
            return true;
        };
    }

    private Action<DeploySSMState, DeploySSMEvent> readyErrorAction() {
        return stateContext -> {
            log.info("readyErrorAction: event {}", stateContext.getMessage().getPayload());
        };
    }

    private Action<DeploySSMState, DeploySSMEvent> readyEntryAction() {
        return stateContext -> {
            log.info("readyEntryAction: event {}", stateContext.getMessage().getPayload());
        };
    }

    private Action<DeploySSMState, DeploySSMEvent> errorEntryAction() {
        return stateContext -> {
            log.info("errorEntryAction: event {}", stateContext.getMessage().getPayload());
        };
    }

}
