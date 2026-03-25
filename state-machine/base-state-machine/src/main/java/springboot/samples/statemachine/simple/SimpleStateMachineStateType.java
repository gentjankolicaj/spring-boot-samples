package springboot.samples.statemachine.simple;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:36 PM
 *
 */
public enum SimpleStateMachineStateType {

    SINITIAL, S0, S1, S2, S3, SEND;


    public static SimpleStateMachineStateType[] allIntermediates() {
        return new SimpleStateMachineStateType[]{S0, S1, S2, S3};
    }
}
