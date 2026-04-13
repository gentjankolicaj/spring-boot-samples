package springboot.samples.reactive.car;

/**
 *
 * @author gentjan kolicaj
 * @since 4/9/26 10:18 AM
 *
 */
public enum CarSMStates {

    CAR_PRESENT, //initial
    CAR_STOPPED, //end

    //Pre enter
    CAR_OPENED, CAR_CLOSED,

    //pre inside
    PRE_INSIDE, IS_CAR_OPEN,

    //post inside
    POST_INSIDE, INSIDE,

    //post enter
    INSIDE_CAR,
    OFF_NOT_READY, OFF_BRAKE_READY, OFF_CLUTCH_READY,
    ENGINE_ON, CHANGE_GEAR, ON_GEAR, MOVING, DRIVING, NEUTRAL, IDLE, PARKING,

    //pseudo states
    CAR_OFF,
    CAR_RUNNING

}
