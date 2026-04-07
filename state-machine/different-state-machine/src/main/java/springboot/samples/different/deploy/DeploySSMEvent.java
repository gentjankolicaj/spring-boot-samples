package springboot.samples.different.deploy;

/**
 *
 * @author gentjan kolicaj
 * @since 4/7/26 12:26 PM
 *
 */
public enum DeploySSMEvent {

    DEPLOY, IS_INSTALLED, INSTALL_OK,
    UNDEPLOY, IS_RUNNING,
    HAS_ERROR,
}
