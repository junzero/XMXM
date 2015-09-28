package com.eos.server.dict;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.gotop.taglib.auth.RoleManager;

public class EosDictListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		DictManager.clearDictCache();
//		System.out.println("----------");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		DictManager.reloadEosDict();
//		System.out.println("========================"); 
//		RoleManager.reloadAuth(); 
	}

}
