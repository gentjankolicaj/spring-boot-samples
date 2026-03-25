package springboot.samples.statemachine.hierarchicalstate;

/**
 *
 * @author gentjan kolicaj
 * @since 3/19/26 8:36 PM
 *
 */
public enum HierarchicalStateMachineStateType {

    SINITIAL, S0, S1, S2, S3, SEND,
    S0_0, S0_END, S1_0, S1_1, S1_2, S1_END, S2_0, S2_END, S3_0, S3_END;


    public static HierarchicalStateMachineStateType[] finalStates() {
        return new HierarchicalStateMachineStateType[]{S0, S1, S2, S2};
    }

}
