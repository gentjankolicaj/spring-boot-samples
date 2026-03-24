package springboot.samples.statemachine.globallisteners;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:36 PM
 *
 */
public enum GlobalListenerStateMachineStateType {

    SSTART, S0, S1, S2, S3, SFINISH;


    public static GlobalListenerStateMachineStateType[] allIntermediates() {
        return new GlobalListenerStateMachineStateType[]{S0, S1};
    }

}
