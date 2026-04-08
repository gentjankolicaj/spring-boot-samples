package springboot.samples.different.ordershipping;

/**
 *
 * @author gentjan kolicaj
 * @since 4/8/26 9:30 AM
 *
 */
public enum OrderShippingState {

    //Root states
    WAIT_NEW_ORDER, RECEIVE_ORDER, CHOICE_HANDLE_ORDER, CUSTOMER_ERROR, HANDLE_ORDER, SHIP_ORDER, ORDER_SHIPPED,

    //Region 1 (stock/production)
    CHECK_STOCK, CHOICE_PRODUCTION, MAKE_PRODUCTION_PLAN, PRODUCE, FILL_ORDER, WAIT_PRODUCT,

    //Region 2 (payment)
    SEND_BILL, WAIT_PAYMENT, NOTIFY_CUSTOMER, WAIT_ORDER, HANDLE_PAYMENT, SEND_REMINDER, CHOICE_PAYMENT_OK,


    //pseudo states
    FORK, JOIN, JUNCTION_STOCK, JUNCTION_ORDER


}
