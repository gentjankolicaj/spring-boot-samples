package org.springboot.samples.jwt_01.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.samples.jwt_01.repository.CountryRepository;
import org.springboot.samples.jwt_01.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
	
	private static final Logger logger= LoggerFactory.getLogger(CountryServiceImpl.class);
	
	@Autowired
	private CountryRepository countryRepository;
	

}
