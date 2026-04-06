package springboot.samples.different.zookeper;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.ensemble.StateMachineEnsemble;
import org.springframework.statemachine.zookeeper.ZookeeperStateMachineEnsemble;

/**
 *
 * @author gentjan kolicaj
 * @since 4/6/26 10:37 AM
 *
 */
@Slf4j
@Configuration
public class ZookeeperSSMConfig {

  @Bean("zookeeperSSM")
  public StateMachine<ZookeeperSSMState, ZookeeperSSMEvent> createStateMachine(
      CuratorFramework curatorFramework) throws Exception {
    StateMachineBuilder.Builder<ZookeeperSSMState, ZookeeperSSMEvent> builder = new StateMachineBuilder.Builder<>();

    //configure states
    builder.configureStates()
        .withStates()
        .initial(ZookeeperSSMState.LOCKED)
        .states(EnumSet.allOf(ZookeeperSSMState.class));

    //configure transitions
    builder.configureTransitions()
        .withExternal()
        .source(ZookeeperSSMState.LOCKED).target(ZookeeperSSMState.UNLOCKED)
        .event(ZookeeperSSMEvent.COIN)
        .and()
        .withExternal()
        .source(ZookeeperSSMState.UNLOCKED).target(ZookeeperSSMState.LOCKED)
        .event(ZookeeperSSMEvent.PUSH);

    //configure configurations
    builder.configureConfiguration()
        .withConfiguration()
        .listener(new ZookeeperSSMListener());

    //configure distributed
    builder.configureConfiguration()
        .withDistributed()
        .ensemble(createStateMachineEnsemble(curatorFramework));

    return builder.build();
  }

  @Bean
  public StateMachineEnsemble<ZookeeperSSMState, ZookeeperSSMEvent> createStateMachineEnsemble(
      CuratorFramework curatorFramework) {
    return new ZookeeperStateMachineEnsemble(curatorFramework, "/distributed-ssm");
  }

  @Bean
  public CuratorFramework createCuratorFramework() {
    CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
    builder.defaultData(new byte[0]);
    builder.retryPolicy(new ExponentialBackoffRetry(1000, 3));
    builder.connectString("localhost:2181");

    CuratorFramework curatorFramework = builder.build();

    // START the client first
    curatorFramework.start();

    try {
      // CRITICAL: Wait until the connection is actually established
      boolean connected = curatorFramework.blockUntilConnected(10, TimeUnit.SECONDS);
      if (!connected) {
        throw new RuntimeException("Time out waiting for Zookeeper connection");
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    try {
      // Ensure the path exists before the State Machine starts
      String path = "/distributed-ssm"; // MUST match your ensemble path
      if (curatorFramework.checkExists().forPath(path) == null) {
        curatorFramework.create().creatingParentsIfNeeded().forPath(path);
      }
    } catch (Exception e) {
      log.error("Could not initialize Zookeeper path", e);
    }
    return curatorFramework;
  }
}
