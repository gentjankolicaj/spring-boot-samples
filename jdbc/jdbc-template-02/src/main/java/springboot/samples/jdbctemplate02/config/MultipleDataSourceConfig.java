package springboot.samples.jdbctemplate02.config;


import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration(proxyBeanMethods = false)
public class MultipleDataSourceConfig {


  @Bean
  @Primary
  @ConfigurationProperties("spring.datasource.mysql")
  public DataSourceProperties mysqlDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @ConfigurationProperties("spring.datasource.postgres")
  public DataSourceProperties postgresDataSourceProperties() {
    return new DataSourceProperties();
  }

  //=================================================================
  //Create mysql datasource & mysql jdbcTemplate
  //==================================================================


  @Bean
  @Primary
  @ConfigurationProperties("spring.datasource.mysql.configuration")
  public DataSource mysqlDataSource() {
    return mysqlDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class)
        .build();
  }

  @Bean(name = "mysqlJdbcTemplate")
  public JdbcTemplate mysqlJdbcTemplate(DataSource mysqlDataSource) {
    return new JdbcTemplate(mysqlDataSource);
  }

  //=================================================================
  //Create postgresql datasource & postgresql jdbcTemplate
  //=================================================================


  @Bean
  @ConfigurationProperties("spring.datasource.postgres.configuration")
  public DataSource postgresDataSource() {
    return postgresDataSourceProperties().initializeDataSourceBuilder().type(BasicDataSource.class)
        .build();
  }


  @Bean(name = "postgresJdbcTemplate")
  public JdbcTemplate jdbcTemplate(DataSource postgresDataSource) {
    return new JdbcTemplate(postgresDataSource);
  }


}
