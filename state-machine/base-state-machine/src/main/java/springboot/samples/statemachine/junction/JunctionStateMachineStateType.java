package springboot.samples.statemachine.junction;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:36 PM
 *
 */
public enum JunctionStateMachineStateType {

    SINITIAL, S0, S1, S2, S3, SEND,
    J0;


    public static JunctionStateMachineStateType[] allIntermediates() {
        return new JunctionStateMachineStateType[]{S0, S1, S2, S3};
    }

}
