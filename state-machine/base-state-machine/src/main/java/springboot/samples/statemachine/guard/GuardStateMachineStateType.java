package springboot.samples.statemachine.guard;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:36 PM
 *
 */
public enum GuardStateMachineStateType {

    SINITIAL, S0, S1, S2, S3, SEND;


    public static GuardStateMachineStateType[] allIntermediates() {
        return new GuardStateMachineStateType[]{S0, S1};
    }

}
