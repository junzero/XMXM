package com.gotop.jbpm.jpdl.model;


import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yeyong
 * 
 */
public class Node {
	private String name;
	private String type;
	private Rectangle rectangle;
	private List<Transition> transitions = new ArrayList<Transition>();

	public Node(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public Node(String name, String type, int x, int y, int w, int h) {
		this.name = name;
		this.type = type;
		this.rectangle = new Rectangle(x, y, w, h);
	}

	/**
	 * @return the rectangle
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}

	/**
	 * @param rectangle
	 *          the rectangle to set
	 */
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *          the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *          the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void addTransition(Transition transition) {
		transitions.add(transition);
	}

	/**
	 * @return the transitions
	 */
	public List<Transition> getTransitions() {
		return transitions;
	}

	/**
	 * @param transitions
	 *          the transitions to set
	 */
	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public int getX() {
		return rectangle.x;
	}

	public int getY() {
		return rectangle.y;
	}

	public int getCenterX() {
		return (int) rectangle.getCenterX();
	}

	public int getCenterY() {
		return (int) rectangle.getCenterY();
	}

	public int getWitdth() {
		return rectangle.width;
	}

	public int getHeight() {
		return rectangle.height;
	}

	@Override
	public String toString(){
	    StringBuffer buf = new StringBuffer();
	    buf.append("Node ( \n");
	    buf.append(" name = ").append(this.name).append("\n");
	    buf.append(" type = ").append(this.type).append("\n");
	    buf.append(" rectangle = ").append(this.rectangle).append("\n");
	    buf.append(" transitions = ").append(this.transitions).append("\n");
	    buf.append(")");    
	    return buf.toString();
	}
	
}
