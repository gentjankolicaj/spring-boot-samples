package springboot.samples.testcontainer_mysql.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
public class LocationRepositoryMySQLTest extends BaseTest {


  @Test
  @Override
  public void create() {

  }

  @Test
  @Override
  public void read() {

  }

  @Test
  @Override
  public void update() {

  }

  @Test
  @Override
  public void delete() {

  }
}