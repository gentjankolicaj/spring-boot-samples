package springboot.samples.different.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.annotation.Secured;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.security.SecurityRule.ComparisonType;

import java.util.EnumSet;
import java.util.HashSet;

@Slf4j
@Configuration
public class SecuritySSMConfig {


    @Bean("securitySSM")
    public StateMachine<SecuritySSMState, SecuritySSMEvent> createStateMachine() throws Exception {
        StateMachineBuilder.Builder<SecuritySSMState, SecuritySSMEvent> builder = new StateMachineBuilder.Builder<>();

        //configure states
        builder.configureStates()
                .withStates()
                .initial(SecuritySSMState.S0)
                .states(new HashSet<>(EnumSet.allOf(SecuritySSMState.class)));

        //configure transitions
        builder.configureTransitions()
                .withInternal().source(SecuritySSMState.S0).event(SecuritySSMEvent.D)
                .action(adminAction()) //secured action execution with role admin
                .and()
                .withExternal().source(SecuritySSMState.S0).target(SecuritySSMState.S1)
                .event(SecuritySSMEvent.A)
                .and()
                .withInternal().source(SecuritySSMState.S1).event(SecuritySSMEvent.F)
                .action(transitionAction())
                .secured("ROLE_ADMIN", ComparisonType.ANY) //secured with role admin
                .and()
                .withExternal().source(SecuritySSMState.S1).target(SecuritySSMState.S2)
                .event(SecuritySSMEvent.B)
                .and()
                .withExternal().source(SecuritySSMState.S2).target(SecuritySSMState.S3)
                .event(SecuritySSMEvent.E)
                .action(transitionAction())
                .secured("ROLE_ADMIN", ComparisonType.ANY) //secured with role admin
                .and()
                .withExternal().source(SecuritySSMState.S2).target(SecuritySSMState.S0)
                .event(SecuritySSMEvent.C)
                .and()
                .withExternal().source(SecuritySSMState.S3).target(SecuritySSMState.S0)
                .event(SecuritySSMEvent.C);

        //configure configuration
        builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(true)
                .and()
                .withSecurity()
                .enabled(true)
                .event("hasRole('USER')");

        return builder.build();
    }


    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Bean
    public Action<SecuritySSMState, SecuritySSMEvent> adminAction() {
        return new Action<>() {

            @Secured("ROLE_ADMIN")
            @Override
            public void execute(StateContext<SecuritySSMState, SecuritySSMEvent> context) {
                log.info("adminAction(): Executed only for admin role");
            }
        };
    }

    @Bean
    public Action<SecuritySSMState, SecuritySSMEvent> transitionAction() {
        return context -> log.info("transitionAction(): Executed only for admin role");
    }

}
