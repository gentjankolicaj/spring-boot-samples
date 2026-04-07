package springboot.samples.different.cdplayer;


import org.springframework.statemachine.annotation.OnTransition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnTransition
public @interface StatesOnTransition {

    States[] source() default {};

    States[] target() default {};

}
