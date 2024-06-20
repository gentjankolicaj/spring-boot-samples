package springboot.samples.jdbctemplate02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JdbcTemplate02Application {

  public static void main(String[] args) {
    SpringApplication.run(JdbcTemplate02Application.class, args);
  }

}
