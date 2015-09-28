package com.gotop.jbpm.dto;

public class ActivityDto {

	private String transitionName;
	
	private String destinationName;
	
	private String actShowName;
	
	private String beforeName;
	
	private String definitionId;
	
	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public String getTransitionName() {
		return transitionName;
	}

	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getActShowName() {
		return actShowName;
	}

	public void setActShowName(String actShowName) {
		this.actShowName = actShowName;
	}

	public String getBeforeName() {
		return beforeName;
	}

	public void setBeforeName(String beforeName) {
		this.beforeName = beforeName;
	}
	
}
