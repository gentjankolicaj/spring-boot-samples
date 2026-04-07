package springboot.samples.different.deploy;

/**
 *
 * @author gentjan kolicaj
 * @since 4/7/26 12:26 PM
 *
 */
public enum DeploySSMState {

    READY,
    DEPLOY, PREPARE_DEPLOY, INSTALL, START, EXIT_DEPLOY,
    UNDEPLOY, PREPARE_UNDEPLOY, EXIT_UNDEPLOY, STOP,
    ERROR,

}
