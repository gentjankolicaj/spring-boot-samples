package springboot.samples.statemachine.builder;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:36 PM
 *
 */
public enum BuilderStateMachineStateType {

    SINITIAL, S0, S1, S2, S3, SEND;


    public static BuilderStateMachineStateType[] allIntermediates() {
        return new BuilderStateMachineStateType[]{S0, S1};
    }

}
