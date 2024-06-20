package springboot.samples.testcontainer_postgres.repository.postgresql;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("postgresql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LocationRepositoryPostgresqlTest extends BaseTest {


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