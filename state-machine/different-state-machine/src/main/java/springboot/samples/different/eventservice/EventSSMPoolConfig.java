package springboot.samples.different.eventservice;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 *
 * @author gentjan kolicaj
 * @since 4/7/26 10:52 AM
 *
 */
@Configuration
public class EventSSMPoolConfig {

    @Bean
    public CommonsPool2TargetSource pool2TargetSource() {
        CommonsPool2TargetSource pool = new CommonsPool2TargetSource();
        pool.setMaxSize(3);
        pool.setTargetBeanName("eventSSM");
        return pool;
    }


    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ProxyFactoryBean stateMachineProxy() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTargetSource(pool2TargetSource());
        return proxyFactoryBean;
    }
}
