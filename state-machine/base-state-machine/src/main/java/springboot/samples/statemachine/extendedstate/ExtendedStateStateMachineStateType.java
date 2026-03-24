package springboot.samples.statemachine.extendedstate;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:36 PM
 *
 */
public enum ExtendedStateStateMachineStateType {

    SSTART, S0, S1, S2, S3, SFINISH;


    public static ExtendedStateStateMachineStateType[] allIntermediates() {
        return new ExtendedStateStateMachineStateType[]{S0, S1};
    }

}
