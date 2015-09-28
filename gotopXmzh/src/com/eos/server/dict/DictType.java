package com.eos.server.dict;

import java.util.List;

public abstract interface DictType
{

  public abstract void setRoles(String roles);
  
  public abstract String getRoles();
	
  public abstract void addDictEntry(DictEntry paramDictEntry);

  public abstract void setDictTypeId(String paramString);

  public abstract String getDictTypeId();

  public abstract void setDictTypeName(String paramString);

  public abstract String getDictTypeName();

  public abstract int getLevel();

  public abstract void setLevel(int paramInt);

  public abstract void setParent(DictType paramDictType);

  public abstract DictType getParent();

  public abstract void setChild(DictType paramDictType);

  public abstract DictType getChild();

//  public abstract List<DictEntry> getDictEntries();
  
  public abstract List<DictEntry> getDictEntries(String roles);

  public abstract List<DictEntry> getDictEntries(String paramString1, String paramString2,String roles);

  public abstract List<DictEntry> getDictEntries(String paramString1, String paramString2, String paramString3,String roles);

  public abstract DictEntry getDictEntryById(String paramString);
}