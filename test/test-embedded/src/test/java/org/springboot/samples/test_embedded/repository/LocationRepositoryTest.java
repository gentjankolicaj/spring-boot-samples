package org.springboot.samples.test_embedded.repository;

import org.junit.jupiter.api.Test;
import org.springboot.samples.test_embedded.repository.base.CrudTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class LocationRepositoryTest extends CrudTest {


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