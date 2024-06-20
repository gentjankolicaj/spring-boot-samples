package springboot.samples.jwt01.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.samples.jwt01.repository.CountryRepository;
import springboot.samples.jwt01.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

  private static final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

  @Autowired
  private CountryRepository countryRepository;


}
