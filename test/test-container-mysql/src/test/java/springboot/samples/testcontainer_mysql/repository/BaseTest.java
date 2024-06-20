package springboot.samples.testcontainer_mysql.repository;

import java.util.function.Supplier;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

//Singleton pattern implemented for creating test containers otherwise performance is low
public abstract class BaseTest {

  static final MySQLContainer MYSQL_CONTAINER = new MySQLContainer("mysql:8.0.26")
      .withDatabaseName("mysql-test-db")
      .withUsername("root")
      .withPassword("password");

  static {
    //start mysql container
    MYSQL_CONTAINER.start();
  }


  /**
   * Set spring.datasource.url value during runtime,because port is dynamic not default. Dynamic
   * port causing connection exception
   */
  @DynamicPropertySource
  static void setDynamicProperties(DynamicPropertyRegistry registry) {
    String dynamicContainerUrl = "jdbc:mysql://" + MYSQL_CONTAINER.getContainerIpAddress() + ":"
        + MYSQL_CONTAINER.getFirstMappedPort() + "/" + MYSQL_CONTAINER.getDatabaseName()
        + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
    Supplier<Object> urlSupplier = () -> dynamicContainerUrl;

    registry.add("spring.datasource.url", urlSupplier);
  }


  public abstract void create();

  public abstract void read();

  public abstract void update();

  public abstract void delete();

}
