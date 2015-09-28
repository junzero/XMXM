package com.gotop.util.rmi;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("DebugEntity")
public class DebugEntity {
	private String name;
	private Integer type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
    public String toString() {
    	return getClass().getName() + "@name=" + this.name+"  type="+this.type;
    }
}
