package springboot.jdbctemplate01.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatasourceConfig {


    /**
     * @Bean : annotation which is applied on a method to specify that it returns a bean to be managed by Spring context.
     * @Primary : to give higher preference to a bean when there are multiple beans of the same type.https://www.baeldung.com/spring-primary
     * @ConfigurationProperties :  @ConfigurationProperties annotation on @Bean-annotated methods.This approach may be particularly useful when we want to bind properties to a third-party component that's outside of our control.https://www.baeldung.com/configuration-properties-in-spring-boot
     */
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.main")
    public HikariDataSource hikariDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }


}
