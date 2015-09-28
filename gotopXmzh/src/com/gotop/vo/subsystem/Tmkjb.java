package com.gotop.vo.subsystem;

/**
 * Tmkjb entity. @author MyEclipse Persistence Tools
 */

public class Tmkjb implements java.io.Serializable {

	// Fields

	private TmkjbId id;

	// Constructors

	/** default constructor */
	public Tmkjb() {
	}

	/** full constructor */
	public Tmkjb(TmkjbId id) {
		this.id = id;
	}

	// Property accessors

	public TmkjbId getId() {
		return this.id;
	}

	public void setId(TmkjbId id) {
		this.id = id;
	}

}