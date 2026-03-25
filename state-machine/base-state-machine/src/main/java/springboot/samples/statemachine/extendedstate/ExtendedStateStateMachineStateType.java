package springboot.samples.statemachine.extendedstate;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:36 PM
 *
 */
public enum ExtendedStateStateMachineStateType {

    SINITIAL, S0, S1, S2, S3, SEND;


    public static ExtendedStateStateMachineStateType[] allIntermediates() {
        return new ExtendedStateStateMachineStateType[]{S0, S1};
    }

}
