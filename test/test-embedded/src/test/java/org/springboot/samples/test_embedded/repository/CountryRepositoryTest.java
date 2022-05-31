package org.springboot.samples.test_embedded.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springboot.samples.test_embedded.repository.base.CrudTest;
import org.springboot.samples.test_embedded.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
public class CountryRepositoryTest extends CrudTest {

    @Autowired
    private CountryRepository countryRepository;

    static Country country=new Country(null,"Albania",1L);

    @BeforeEach
    public void beforeEach(){
        country=countryRepository.save(country);
    }


    @Test
    @Override
    public void create() {
     Country country=new Country(null,"USA",1L);
     Country savedCountry=countryRepository.save(country);
     assertThat(savedCountry).usingRecursiveComparison().ignoringFields("countryId").isEqualTo(country);

    }

    @Override
    @Test
    public void read() {
        Optional<Country> optionalCountry=countryRepository.findById(country.getCountryId());
        assertThat(optionalCountry.get()).isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("countryId")
                .isEqualTo(country);
    }

    @Override
    @Test
    public void update() {
        Optional<Country> optionalCountry=countryRepository.findById(country.getCountryId());

        Country actualCountry=optionalCountry.get();
        actualCountry.setCountryName("USA");

        Country updatedCountry= countryRepository.saveAndFlush(actualCountry);
        assertThat(updatedCountry).usingRecursiveComparison().isEqualTo(actualCountry);

    }

    @Override
    @Test
    public void delete() {
         countryRepository.deleteById(country.getCountryId());
        Optional<Country> optionalCountry=countryRepository.findById(1L);
        assertThat(optionalCountry).isEmpty();
    }
}