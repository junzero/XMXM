package com.gotop.tyjg.testwsdl.service.impl;

import org.apache.log4j.Logger;

import com.gotop.tyjg.testwsdl.service.IAuthenticationService;

public class AuthenticationService implements IAuthenticationService {
	protected static Logger log = Logger.getLogger(AuthenticationService.class);

	public String helloWord(String name) {
		log.info("---------------webService OK----"+name);
		return "Hello "+name;
	}
}
