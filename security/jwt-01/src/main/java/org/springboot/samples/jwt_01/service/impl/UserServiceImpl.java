package org.springboot.samples.jwt_01.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.samples.jwt_01.repository.UserRepository;
import org.springboot.samples.jwt_01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	

}
