package com.gotop.jbpm.jpdl.model;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yeyong
 * 
 */
public class Transition {
	private Point labelPosition;
	private List<Point> lineTrace = new ArrayList<Point>();
	private String label;
	private String to;

	public Transition(String label, String to) {
		this.label = label;
		this.to = to;
	}

	/**
	 * @return the labelPosition
	 */
	public Point getLabelPosition() {
		return labelPosition;
	}

	/**
	 * @param labelPosition
	 *          the labelPosition to set
	 */
	public void setLabelPosition(Point labelPosition) {
		this.labelPosition = labelPosition;
	}

	/**
	 * @return the lineTrace
	 */
	public List<Point> getLineTrace() {
		return lineTrace;
	}

	/**
	 * @param lineTrace
	 *          the lineTrace to set
	 */
	public void setLineTrace(List<Point> lineTrace) {
		this.lineTrace = lineTrace;
	}

	public void addLineTrace(Point lineTrace) {
		if (lineTrace != null) {
			this.lineTrace.add(lineTrace);
		}
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *          the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *          the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Transition ( \n");
		buf.append(" labelPosition = ").append(this.labelPosition).append("\n");
		buf.append(" lineTrace = ").append(this.lineTrace).append("\n");
		buf.append(" label = ").append(this.label).append("\n");
		buf.append(" to = ").append(this.to).append("\n");
		buf.append(")");
		return buf.toString();
	}

}
