package com.gotop.util.export;

import java.util.ArrayList;
import java.util.List;

public class QLExportItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8394604050403328742L;

	private String id;

	private String seq;

	private String code;

	private String codeAlias;

	private String name;
	
	private String value;

	private String width;

	private String codeWidth;

	private String codeHeight;

	private String codeValue;
	
	private String rowspan;
	
	private String colspan;
	
	private String height;
	
	private String type;
	
	private String alignment; 
	
	private String breakBefore;
	
	private String bytesLength="0";
	
	private Integer cellType;
	
	private List<QLExportItem> ListTH = new ArrayList<QLExportItem>();

	public QLExportItem(){
		
	}
	
	public QLExportItem(String value, Object width, Object height, Object rowspan,Object colspan) {
		if(value == null){
			this.value = "";
		}else{
			this.value = value;
		}
		this.width = String.valueOf(width);
		this.height = String.valueOf(height);
		this.rowspan = String.valueOf(rowspan);
		this.colspan = String.valueOf(colspan);
		if("null".equalsIgnoreCase(this.width)){
			this.width = null;
		}
		if("null".equalsIgnoreCase(this.height)){
			this.height = null;
		}
		if("null".equalsIgnoreCase(this.rowspan)){
			this.rowspan = null;
		}
		if("null".equalsIgnoreCase(this.colspan)){
			this.colspan = null;
		}
		
	}
	
	public QLExportItem(String id, String seq, String name,String code, String value, String width, String height, String rowspan,String colspan) {
		this.id = id;
		this.seq = seq;
		this.name = name;
		this.code = code;
		this.value = value;
		this.width = width;
		this.height = height;
		this.rowspan = rowspan;
		this.colspan = colspan;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeAlias() {
		return codeAlias;
	}

	public void setCodeAlias(String codeAlias) {
		this.codeAlias = codeAlias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getColspan() {
		return colspan;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getRowspan() {
		return rowspan;
	}

	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public String getBreakBefore() {
		return breakBefore;
	}

	public void setBreakBefore(String breakBefore) {
		this.breakBefore = breakBefore;
	}

	public String getBytesLength() {
		return bytesLength;
	}

	public void setBytesLength(String bytesLength) {
		this.bytesLength = bytesLength;
	}

	public Integer getCellType() {
		return cellType;
	}

	public void setCellType(Integer cellType) {
		this.cellType = cellType;
	}

	public String getCodeHeight() {
		return codeHeight;
	}

	public void setCodeHeight(String codeHeight) {
		this.codeHeight = codeHeight;
	}

	public String getCodeWidth() {
		return codeWidth;
	}

	public void setCodeWidth(String codeWidth) {
		this.codeWidth = codeWidth;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<QLExportItem> getListTH() {
		return ListTH;
	}

	public void setListTH(List<QLExportItem> listTH) {
		ListTH = listTH;
	}
	
	public void addListTH(QLExportItem qlei){
		this.ListTH.add(qlei);
	}
}
