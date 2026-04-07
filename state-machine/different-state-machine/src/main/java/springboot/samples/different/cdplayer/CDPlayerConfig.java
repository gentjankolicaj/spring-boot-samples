package springboot.samples.different.cdplayer;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineBuilder.Builder;

@Configuration
public class CDPlayerConfig {


    @Bean("CDPlayerMachine")
    public StateMachine<States, Events> createStateMachine() throws Exception {
        StateMachineBuilder.Builder<States, Events> builder = new Builder<>();

        //configure states
        builder.configureStates()
                .withStates()
                .initial(States.IDLE)
                .state(States.IDLE)
                .and()
                .withStates() //IDLE level
                .parent(States.IDLE)
                .initial(States.CLOSED)
                .state(States.CLOSED)
                .state(States.OPEN)
                .and()
                .withStates()
                .state(States.BUSY)
                .and()
                .withStates()
                .parent(States.BUSY) //BUSY level
                .initial(States.PLAYING)
                .state(States.PLAYING)
                .state(States.PAUSED);

        //configure transitions
        builder.configureTransitions()
                .withExternal()
                .source(States.CLOSED).target(States.OPEN).event(Events.EJECT)
                .and()
                .withExternal()
                .source(States.OPEN).target(States.CLOSED).event(Events.EJECT)
                .and()
                .withExternal()
                .source(States.OPEN).target(States.CLOSED).event(Events.PLAY)
                .and()
                .withExternal()
                .source(States.PLAYING).target(States.PAUSED).event(Events.PAUSE)
                .and()
                .withInternal()
                .source(States.PLAYING)
                .action(playingAction())
                .timer(1000)
                .and()
                .withInternal()
                .source(States.PLAYING).event(Events.BACK)
                .action(trackAction())
                .and()
                .withInternal()
                .source(States.PLAYING).event(Events.FORWARD)
                .action(trackAction())
                .and()
                .withExternal()
                .source(States.PAUSED).target(States.PLAYING).event(Events.PAUSE)
                .and()
                .withExternal()
                .source(States.BUSY).target(States.IDLE).event(Events.STOP)
                .and()
                .withExternal()
                .source(States.IDLE).target(States.BUSY).event(Events.PLAY)
                .action(playAction())
                .guard(playGuard())
                .and()
                .withInternal()
                .source(States.OPEN).event(Events.LOAD).action(loadAction());

        //configure configuration
        return builder.build();
    }

    @Bean
    public ClosedEntryAction closedEntryAction() {
        return new ClosedEntryAction();
    }

    @Bean
    public LoadAction loadAction() {
        return new LoadAction();
    }

    @Bean
    public TrackAction trackAction() {
        return new TrackAction();
    }

    @Bean
    public PlayAction playAction() {
        return new PlayAction();
    }

    @Bean
    public PlayingAction playingAction() {
        return new PlayingAction();
    }

    @Bean
    public PlayGuard playGuard() {
        return new PlayGuard();
    }

}
