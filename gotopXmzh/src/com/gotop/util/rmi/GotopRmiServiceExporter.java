package com.gotop.util.rmi;

import org.springframework.remoting.rmi.RmiServiceExporter;

public class GotopRmiServiceExporter extends RmiServiceExporter{

	public GotopRmiServiceExporter(){
		super();
		this.setRegistryPort(7080);
		this.setServicePort(7081);
	}
}
