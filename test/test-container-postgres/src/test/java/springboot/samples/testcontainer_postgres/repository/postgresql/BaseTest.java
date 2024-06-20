package springboot.samples.testcontainer_postgres.repository.postgresql;

import java.util.function.Supplier;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class BaseTest {

  static final PostgreSQLContainer POSTGRESQL_CONTAINER = new PostgreSQLContainer()
      .withDatabaseName("postgresql-test-db")
      .withUsername("root")
      .withPassword("password");

  static {
    //start postgresql container
    POSTGRESQL_CONTAINER.start();
  }


  /**
   * Set spring.datasource.url value during runtime,because port is dynamic not default. Dynamic
   * port causing connection exception
   */
  @DynamicPropertySource
  static void setDynamicProperties(DynamicPropertyRegistry registry) {
    String dynamicContainerUrl =
        "jdbc:postgresql://" + POSTGRESQL_CONTAINER.getContainerIpAddress() + ":"
            + POSTGRESQL_CONTAINER.getFirstMappedPort() + "/"
            + POSTGRESQL_CONTAINER.getDatabaseName();
    Supplier<Object> urlSupplier = () -> dynamicContainerUrl;
    registry.add("spring.datasource.url", urlSupplier);
  }


  public abstract void create();

  public abstract void read();

  public abstract void update();

  public abstract void delete();

}
