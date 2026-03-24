package springboot.samples.tenisstatemachine;


import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableStateMachine
public class TenisStateMachineConfig extends StateMachineConfigurerAdapter<TenisStateType, String> {

  private static final String S = "s";
  private static final String O = "o";

  @Override
  public void configure(StateMachineStateConfigurer<TenisStateType, String> states)
          throws Exception {
    states.withStates()
            .initial(TenisStateType.LOVE)
            .end(TenisStateType.END)
            .states(new HashSet<>(Arrays.asList(TenisStateType.intermediateStates())));
  }


  @Override
  public void configure(StateMachineTransitionConfigurer<TenisStateType, String> transitions)
          throws Exception {
    transitions
            //first phase
            .withExternal().source(TenisStateType.LOVE).target(TenisStateType._15_LOVE).event(S).and()
            .withExternal().source(TenisStateType.LOVE).target(TenisStateType.LOVE_15).event(O).and()

            //second phase
            .withExternal().source(TenisStateType._15_LOVE).target(TenisStateType._30_LOVE).event(S)
            .and()
            .withExternal().source(TenisStateType._15_LOVE).target(TenisStateType._15_ALL).event(O)
            .and()
            .withExternal().source(TenisStateType.LOVE_15).target(TenisStateType._15_ALL).event(S).and()
            .withExternal().source(TenisStateType.LOVE_15).target(TenisStateType.LOVE_30).event(O).and()

            //Third phase
            .withExternal().source(TenisStateType._30_LOVE).target(TenisStateType._40_LOVE).event(S)
            .and()
            .withExternal().source(TenisStateType._30_LOVE).target(TenisStateType._30_15).event(O).and()
            .withExternal().source(TenisStateType._15_ALL).target(TenisStateType._30_15).event(S).and()
            .withExternal().source(TenisStateType._15_ALL).target(TenisStateType._15_30).event(O).and()
            .withExternal().source(TenisStateType.LOVE_30).target(TenisStateType._15_30).event(S).and()
            .withExternal().source(TenisStateType.LOVE_30).target(TenisStateType.LOVE_40).event(O).and()

            //Forth phase
            .withExternal().source(TenisStateType._40_LOVE).target(TenisStateType.SERVER_WIN).event(S).and()
            .withExternal().source(TenisStateType._40_LOVE).target(TenisStateType._40_15).event(O).and()
            .withExternal().source(TenisStateType._30_15).target(TenisStateType._40_15).event(S).and()
            .withExternal().source(TenisStateType._30_15).target(TenisStateType._30_ALL).event(O).and()
            .withExternal().source(TenisStateType._15_30).target(TenisStateType._30_ALL).event(S).and()
            .withExternal().source(TenisStateType._15_30).target(TenisStateType._40_15).event(O).and()
            .withExternal().source(TenisStateType.LOVE_40).target(TenisStateType._15_40).event(S).and()
            .withExternal().source(TenisStateType.LOVE_40).target(TenisStateType.OPPONENT_WIN).event(O)
            .and()

            //Fifth phase
            .withExternal().source(TenisStateType._40_15).target(TenisStateType.SERVER_WIN).event(S).and()
            .withExternal().source(TenisStateType._40_15).target(TenisStateType._40_30).event(O).and()
            .withExternal().source(TenisStateType._30_ALL).target(TenisStateType._40_30).event(S).and()
            .withExternal().source(TenisStateType._30_ALL).target(TenisStateType._30_40).event(O).and()
            .withExternal().source(TenisStateType._15_40).target(TenisStateType._30_40).event(S).and()
            .withExternal().source(TenisStateType._15_40).target(TenisStateType.OPPONENT_WIN).event(O).and()

            //Sixth phase
            .withExternal().source(TenisStateType._40_30).target(TenisStateType.SERVER_WIN).event(S).and()
            .withExternal().source(TenisStateType._40_30).target(TenisStateType.DEUCE).event(O).and()
            .withExternal().source(TenisStateType._30_40).target(TenisStateType.OPPONENT_WIN).event(O).and()
            .withExternal().source(TenisStateType._30_40).target(TenisStateType.DEUCE).event(S).and()

            //Deuce phase
            .withExternal().source(TenisStateType.DEUCE).target(TenisStateType.AD_IN).event(S).and()
            .withExternal().source(TenisStateType.DEUCE).target(TenisStateType.AD_OUT).event(O).and()


            //Ad phase
            .withExternal().source(TenisStateType.AD_IN).target(TenisStateType.SERVER_WIN).event(S).and()
            .withExternal().source(TenisStateType.AD_IN).target(TenisStateType.DEUCE).event(O).and()
            .withExternal().source(TenisStateType.AD_OUT).target(TenisStateType.DEUCE).event(S).and()
            .withExternal().source(TenisStateType.AD_OUT).target(TenisStateType.OPPONENT_WIN).event(O).and()

            //Dead phase
            .withExternal().source(TenisStateType.SERVER_WIN).target(TenisStateType.END).event("end").and()
            .withExternal().source(TenisStateType.SERVER_WIN).target(TenisStateType.END).event("end");

  }

  @Override
  public void configure(StateMachineConfigurationConfigurer<TenisStateType, String> config) throws Exception {
    config.withConfiguration()
            .listener(new TenisStateMachineListener());
  }


}
