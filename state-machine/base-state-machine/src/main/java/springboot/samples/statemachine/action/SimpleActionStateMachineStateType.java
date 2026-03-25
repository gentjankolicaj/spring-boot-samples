package springboot.samples.statemachine.action;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:36 PM
 *
 */
public enum SimpleActionStateMachineStateType {

    SINITIAL, S0, S1, S2, S3, SEND;


    public static SimpleActionStateMachineStateType[] allIntermediates() {
        return new SimpleActionStateMachineStateType[]{S0, S1};
    }

}
