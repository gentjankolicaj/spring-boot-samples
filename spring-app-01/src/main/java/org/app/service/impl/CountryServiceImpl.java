package org.app.service.impl;

import org.app.repository.CountryRepository;
import org.app.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
	
	private static final Logger logger= LoggerFactory.getLogger(CountryServiceImpl.class);
	
	@Autowired
	private CountryRepository countryRepository;
	

}
