package springboot.samples.statemachine.globallisteners;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:36 PM
 *
 */
public enum GlobalListenerStateMachineStateType {

    SINITIAL, S0, S1, S2, S3, SEND;


    public static GlobalListenerStateMachineStateType[] allIntermediates() {
        return new GlobalListenerStateMachineStateType[]{S0, S1};
    }

}
