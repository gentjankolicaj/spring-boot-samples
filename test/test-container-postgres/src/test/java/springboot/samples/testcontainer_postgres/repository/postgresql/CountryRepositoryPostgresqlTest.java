package springboot.samples.testcontainer_postgres.repository.postgresql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import springboot.samples.testcontainer_postgres.model.Country;
import springboot.samples.testcontainer_postgres.repository.CountryRepository;

@DataJpaTest
@ActiveProfiles("postgresql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CountryRepositoryPostgresqlTest extends BaseTest {

  Country country = new Country(null, "Albania", 1L);
  @Autowired
  private CountryRepository countryRepository;

  @BeforeEach
  public void beforeEach() {
    country = countryRepository.save(country);
  }

  @Test
  @Override
  public void create() {
    Country country = new Country(null, "USA", 1L);
    Country savedCountry = countryRepository.save(country);
    assertThat(savedCountry).usingRecursiveComparison().ignoringFields("countryId")
        .isEqualTo(country);

  }

  @Override
  @Test
  public void read() {
    Optional<Country> optionalCountry = countryRepository.findById(country.getCountryId());
    assertThat(optionalCountry.get()).isNotNull()
        .usingRecursiveComparison()
        .ignoringFields("countryId")
        .isEqualTo(country);
  }

  @Override
  @Test
  public void update() {
    Optional<Country> optionalCountry = countryRepository.findById(country.getCountryId());

    Country actualCountry = optionalCountry.get();
    actualCountry.setCountryName("USA");

    Country updatedCountry = countryRepository.saveAndFlush(actualCountry);
    assertThat(updatedCountry).usingRecursiveComparison().isEqualTo(actualCountry);

  }

  @Override
  @Test
  public void delete() {
    countryRepository.deleteById(country.getCountryId());
    Optional<Country> optionalCountry = countryRepository.findById(1L);
    assertThat(optionalCountry).isEmpty();
  }


}

