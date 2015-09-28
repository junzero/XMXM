/*******************************************************************************
 * $Header$
 * $Revision$
 * $Date$
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2011-10-5
 *******************************************************************************/

package com.primeton.utils;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("page")
public class Page implements java.io.Serializable{
    int begin=0;
    int length=10;
    int count;
    int totalPage;
    int currentPage;
    boolean isCount;
    boolean isFirst;
    boolean isLast;
    int size;
    
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		isCount = true;
		this.count = count;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
//	public boolean isCount() {
//		return isCount;
//	}
	public void setCount(boolean isCount) {
		this.isCount = isCount;
	}
	public boolean isFirst() {
		return isFirst;
	}
	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	public boolean isLast() {
		return isLast;
	}
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getIsCount(){
		return String.valueOf(isCount);
	}
	public void setIsCount(String isCount){
		if(StringUtils.isNotBlank(isCount)){
			this.isCount = Boolean.valueOf(isCount);
		}else{
			this.isCount = false;
		}
	}
	public boolean getIsFirst(){
		return isFirst;
	}
	public boolean getIsLast(){
		return isLast;
	}
	
}
