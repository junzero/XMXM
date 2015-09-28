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
 * Created on 2009-8-5
 *******************************************************************************/

package com.primeton.utils;

import java.util.List;

public class pageCondExpand {
    int begin;
    int length;
    int count;
    int totalPage;
    int currentPage;
    boolean isCount;
    boolean isFirst;
    boolean isLast;
    int size;
	
    /**
     * 合并两数组
     * @param s
     * @param t
     * @return
     */
	public Object[] joinArra(Object[] s, Object[] t) {
		if (s == null) {
			return t;
		}
		if (t == null) {
			return s;
		}
		Object[] o = new Object[s.length + t.length];
		for (int i = 0; i < s.length; i++) {
			o[i] = s[i];
		}
		for (int i = 0; i < t.length; i++) {
			o[i + s.length] = t[i];
		}

		return o;
	}
	/**
	 * 生成分页信息
	 * @param action
	 * @param pageno
	 */
    protected void checkPagination(String action, int pageno)
    {
        if(begin < 0)
            begin = 0;
        if(length <= 0)
            length = 10;
        size = 0;
        if(action != null)
        {
            if("goto".equals(action))
                currentPage = pageno;
            else
            if("first".equals(action))
                currentPage = 1;
            else
            if("next".equals(action))
                currentPage++;
            else
            if("previous".equals(action))
                currentPage--;
            else
            if("last".equals(action))
            {
                if(isCount && count > 0)
                    currentPage = count / length + 1;
            } else
            {
                currentPage = begin / length + 1;
            }
        } else
        {
            currentPage = begin / length + 1;
        }
        if(currentPage <= 0)
            currentPage = 1;
        if(!isCount)
        {
            totalPage = currentPage;
            isFirst = currentPage == 1;
            isLast = false;
            begin = (currentPage - 1) * length;
            count = -2;
            return;
        }
        if(isCount && count > 0)
        {
            if(count % length == 0)
                totalPage = count / length;
            else
                totalPage = count / length + 1;
            if(currentPage > totalPage)
                currentPage = totalPage;
            begin = (currentPage - 1) * length;
            isFirst = currentPage == 1;
            isLast = totalPage == currentPage;
        } else
        if(count == 0)
        {
            currentPage = 0;
            totalPage = 0;
            begin = 0;
            isFirst = true;
            isLast = true;
        }
    }
	/**
	 * 通过已有的分页信息,计算出新的分页信息
	 * @param objs
	 * @param pageCond
	 */
	public void putPageCond(Page pageCond)
	{
		fetchPagination(pageCond);
		checkPagination(null,0);
		size = length;
		
		
		pageCond.setBegin(begin);
		pageCond.setLength(length);
//		pageCond.setCount(count);
		pageCond.setTotalPage(totalPage);
		pageCond.setCurrentPage(currentPage);
		pageCond.setCount(isCount);
		pageCond.setFirst(isFirst);
		pageCond.setLast(isLast);
		pageCond.setSize(size);
	}
	/**
	 * 读取page对象
	 * @param pageCond
	 */
    protected void fetchPagination(Page pageCond)
    {
        try
        {
            this.begin =  pageCond.getBegin();
            this.count =  pageCond.getCount();
            this.length = pageCond.getLength();
            this.totalPage = pageCond.getTotalPage();
            this.currentPage = pageCond.getCurrentPage();
            this.isCount = pageCond.isCount;
            this.isLast = pageCond.isLast();
            this.isFirst = pageCond.isFirst();
            this.size = pageCond.getSize();
        }
        catch(Throwable t) { }
    }
    
    public static void main(String[] arg){

    }
}
