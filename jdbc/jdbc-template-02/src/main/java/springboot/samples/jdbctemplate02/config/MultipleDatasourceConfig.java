package springboot.samples.jdbctemplate02.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MultipleDatasourceConfig {

    //=================================================================
    //Create postgresql datasource & postgresql jdbcTemplate
    //=================================================================
    @Bean(name = "postgresDataSourceProperties")
    @ConfigurationProperties(prefix = "app.datasource.postgresql")
    public DataSourceProperties postgresqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "postgresDataSource")
    @ConfigurationProperties(prefix = "app.datasource.postgresql.configuration")
    public HikariDataSource postgresqlDataSource(@Qualifier("postgresDataSourceProperties") DataSourceProperties postgresqlDataSourceProperties) {
        return postgresqlDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "postgresJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("postgresDataSource") HikariDataSource postgresqlDataSource) {
        return new JdbcTemplate(postgresqlDataSource);
    }


    //=================================================================
    //Create mysql datasource & mysql jdbcTemplate
    //==================================================================
    @Bean(name = "mysqlDataSourceProperties")
    @ConfigurationProperties(prefix = "app.datasource.mysql")
    public DataSourceProperties mysqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "app.datasource.mysql.configuration")
    public HikariDataSource mysqlDataSource(@Qualifier("mysqlDataSourceProperties") DataSourceProperties mysqlDataSourceProperties) {
        return mysqlDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") HikariDataSource mysqlDataSource) {
        return new JdbcTemplate(mysqlDataSource);
    }


}
