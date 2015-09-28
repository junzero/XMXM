// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   IPopMenuItem.java

package com.eos.web.taglib.webcomp.popmenu;


public interface IPopMenuItem
{

    public abstract String getId();

    public abstract void setId(String s);

    public abstract int addChild();

    public abstract void clearChild();
}
