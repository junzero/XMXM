package com.gotop.util.rmi;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;

public class GotopRmiProxyFactoryBean extends RmiProxyFactoryBean{
	
	public void setServiceUrl(String serviceUrl){
		super.setServiceUrl("rmi://localhost:7080/"+serviceUrl);
	}
	
	public GotopRmiProxyFactoryBean(){
		super();
	}
	
}
