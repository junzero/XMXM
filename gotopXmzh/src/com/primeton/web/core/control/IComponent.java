// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   IComponent.java

package com.primeton.web.core.control;


public interface IComponent
{

    public abstract void setAttribute(String s, String s1);

    public abstract String getAttribute(String s);

    public abstract String toHtml()
        throws Exception;
}
