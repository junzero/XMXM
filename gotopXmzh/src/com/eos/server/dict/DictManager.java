// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DictManager.java

package com.eos.server.dict;

import com.eos.common.cache.CacheFactory;
import com.eos.common.cache.ICache;
import com.eos.infra.config.Configuration;
import com.eos.server.dict.config.DictConfigModel;
import com.eos.web.taglib.util.StringUtil;
import com.primeton.ext.data.vmodel.Field;
import java.util.*;

import sun.util.logging.resources.logging;

// Referenced classes of package com.eos.server.dict:
//            DictType, DictEntry, DictFactoryConfig, DictFactory

public class DictManager
{

    public static Map dictmap = new HashMap();
    public static Map dictEntryI18n = new HashMap();
    public static Map dictTypeI18n = new HashMap();
    public static final String DICTTYPEI18N = "_EOS_DICT_TYPE_I18N_";
    public static final String DICTENTRYI18N = "_EOS_DICT_ENTRY_I18N_";
    private static ICache dictCache = null;
    private static boolean useCache = false;
    private static boolean isLoad = false;
    private static Object loadLock = new Object();
    public static List factoryConfigs = new ArrayList();

    public DictManager()
    {
    }

    public static String getDictTypeName(String dictTypeId)
    {
        return getDictType(dictTypeId).getDictTypeName();
    }

    public static String getDictTypeNameI18n(String dictTypeId, String locale)
    {
        String dictTypeName = (String)getDictTypeI18n().get((new StringBuilder()).append(dictTypeId).append(locale).toString());
        if(dictTypeName != null)
            return dictTypeName;
        else
            return getDictType(dictTypeId).getDictTypeName();
    }

    public static DictType getDictType(String dictTypeId)
    {
        if(getDictMap().containsKey(dictTypeId))
            return (DictType)getDictMap().get(dictTypeId);
        else
            return null;
    }

    public static String getDictName(String dictTypeId, String dictId,String roles)
    {
        return getDictName(dictTypeId, dictId, 0,roles);
    }
    
    public static String getDictName(String dictTypeId, String dictId)
    {
    	return getDictName(dictTypeId, dictId, 0,null);
    }

    public static String getDictNameI18n(String dictTypeId, String dictId, String locale,String roles)
    {
        String dictName = (String)getDictEntryI18n().get((new StringBuilder()).append(dictTypeId).append(dictId).append(locale).toString());
        if(dictName != null)
            return dictName;
        else
            return getDictName(dictTypeId, dictId, 0,roles);
    }

    public static String getDictName(String dictTypeId, String dictId, int level,String roles)
    {
        return getDictName(getDictType(dictTypeId), dictId, level,roles);
    }

    public static String getDictNameI18n(String dictTypeId, String dictId, int level, String locale,String roles)
    {
        return getDictNameI18n(getDictType(dictTypeId), dictId, level, locale,roles);
    }

    public static String getDictName(DictType dictType, String dictId,String roles)
    {
        List entries = dictType.getDictEntries(roles);
        for(int i = 0; i < entries.size(); i++)
        {
            DictEntry dict = (DictEntry)entries.get(i);
            if(dictId.equals(dict.getDictId()))
                return dict.getDictName();
        }

        return dictId;
    }
    
    public static String getDictName(List<DictEntry> entries, String dictId)
    {
    	for(int i = 0; i < entries.size(); i++)
    	{
    		DictEntry dict = (DictEntry)entries.get(i);
    		if(dictId.equals(dict.getDictId()))
    			return dict.getDictName();
    	}
    	
    	return dictId;
    }

    public static String getDictNameI18n(DictType dictType, String dictId, String locale,String roles)
    {
        String dictTypeId = dictType.getDictTypeId();
        String dictName = (String)getDictEntryI18n().get((new StringBuilder()).append(dictTypeId).append(dictId).append(locale).toString());
        if(dictName != null)
            return dictName;
        else
            return getDictName(dictType, dictId,null);
    }

    public static DictEntry getDictEntry(DictType dictType, String dictId,String roles)
    {
        List entries = dictType.getDictEntries(roles);
        for(int i = 0; i < entries.size(); i++)
        {
            DictEntry dict = (DictEntry)entries.get(i);
            if(dictId.equals(dict.getDictId()))
                return dict;
        }

        return null;
    }
    
    public static DictEntry getDictEntry(DictType dictType, String dictId)
    {
    	List entries = dictType.getDictEntries(null);
    	for(int i = 0; i < entries.size(); i++)
    	{
    		DictEntry dict = (DictEntry)entries.get(i);
    		if(dictId.equals(dict.getDictId()))
    			return dict;
    	}
    	
    	return null;
    }

    public static String getDictName(DictType dictType, String dictId, int level,String roles)
    {
        if(level == 0)
            return getDictName(dictType, dictId, roles);
        if(level == -1)
        {
            ArrayList list = new ArrayList(5);
            DictEntry entry = getDictEntry(dictType, dictId,roles);
            list.add(entry.getDictName());
            for(DictEntry parentEntry = entry.getParent(); parentEntry != null; parentEntry = parentEntry.getParent())
                list.add(parentEntry.getDictName());

            StringBuilder buf = new StringBuilder();
            boolean isFirst = true;
            for(int i = list.size() - 1; i >= 0; i--)
            {
                if(!isFirst)
                    buf.append("-");
                else
                    isFirst = false;
                buf.append((String)list.get(i));
            }

            return buf.toString();
        }
        if(level == dictType.getLevel())
            return getDictName(dictType, dictId,roles);
        if(level > dictType.getLevel() || level < -1)
            return null;
        DictEntry entry = getDictEntry(dictType, dictId,roles);
        DictEntry parentEntry;
        for(parentEntry = entry.getParent(); level < parentEntry.getLevel(); parentEntry = parentEntry.getParent());
        return parentEntry.getDictName();
    }

    public static String getDictNameI18n(DictType dictType, String dictId, int level, String locale,String roles)
    {
        if(level == 0)
            return getDictNameI18n(dictType, dictId, locale,roles);
        if(level == -1)
        {
            ArrayList list = new ArrayList(5);
            list.add(getDictNameI18n(dictType, dictId, locale,roles));
            DictEntry entry = getDictEntry(dictType, dictId,null);
            DictEntry parentEntry = entry.getParent();
            for(DictType parentType = dictType.getParent(); parentEntry != null; parentType = parentType.getParent())
            {
                list.add(getDictNameI18n(parentType, parentEntry.getDictId(), locale,roles));
                parentEntry = parentEntry.getParent();
            }

            StringBuilder buf = new StringBuilder();
            boolean isFirst = true;
            for(int i = list.size() - 1; i >= 0; i--)
            {
                if(!isFirst)
                    buf.append("-");
                else
                    isFirst = false;
                buf.append((String)list.get(i));
            }

            return buf.toString();
        }
        if(level == dictType.getLevel())
            return getDictNameI18n(dictType, dictId, locale,roles);
        if(level > dictType.getLevel() || level < -1)
            return null;
        DictEntry entry = getDictEntry(dictType, dictId,null);
        DictEntry parentEntry = entry.getParent();
        DictType parentType;
        for(parentType = dictType.getParent(); level < parentEntry.getLevel(); parentType = parentType.getParent())
            parentEntry = parentEntry.getParent();

        return getDictNameI18n(parentType, parentEntry.getDictId(), locale,roles);
    }

    public static String getDictNames(String dictTypeId, String dictIds,String roles)
    {
        return getDictNames(getDictType(dictTypeId), dictIds, null,roles);
    }

    public static String getDictNamesI18n(String dictTypeId, String dictIds, String locale,String roles)
    {
        return getDictNamesI18n(getDictType(dictTypeId), dictIds, null, locale,roles);
    }

    public static String getDictNamesI18n(String dictTypeId, String dictIds, String seperator, String locale,String roles)
    {
        return getDictNamesI18n(getDictType(dictTypeId), dictIds, seperator, locale,roles);
    }

    public static String getDictNames(String dictTypeId, String dictIds, String seperator,String roles)
    {
        return getDictNames(getDictType(dictTypeId), dictIds, seperator,roles);
    }

    public static String getDictNames(DictType dictType, String dictIds, String seperator,String roles)
    {
        List dictList = StringUtil.splitString(dictIds, seperator);
        StringBuilder buf = new StringBuilder();
        boolean isFirst = true;
        for(int i = 0; i < dictList.size(); i++)
        {
            if(!isFirst)
                buf.append(",");
            else
                isFirst = false;
            buf.append(getDictName(dictType, (String)dictList.get(i),roles));
        }

        return buf.toString();
    }

    public static String getDictNamesI18n(DictType dictType, String dictIds, String seperator, String locale,String roles)
    {
        List dictList = StringUtil.splitString(dictIds, seperator);
        StringBuilder buf = new StringBuilder();
        boolean isFirst = true;
        for(int i = 0; i < dictList.size(); i++)
        {
            if(!isFirst)
                buf.append(",");
            else
                isFirst = false;
            buf.append(getDictNameI18n(dictType, (String)dictList.get(i), locale,roles));
        }

        return buf.toString();
    }

    public static List getDictEntries(String dictTypeId,String roles)
    {
    	DictType dict = getDictType(dictTypeId);
    	dict.setRoles(roles);
    	if(dict == null)
    		return new ArrayList();
    	else
    		return dict.getDictEntries(roles);
    }
    public static List getDictEntries(String dictTypeId)
    {
    	DictType dict = getDictType(dictTypeId);
    	if(dict == null)
    		return new ArrayList();
    	else
    		return dict.getDictEntries(null);
    }

    public static List getDictEntries(String dictTypeId, int level,String roles)
    {
        List rootEntries = getDictEntries(dictTypeId,roles);
        List outlist = new ArrayList();
        if(level == 0 || level < -1)
            return rootEntries;
        if(level == -1)
            level = 0x186a0;
        int cnt = 0;
        for(int i = 0; i < rootEntries.size(); i++)
        {
            DictEntry rootEntry = (DictEntry)rootEntries.get(i);
            searchDescendantEntries(outlist, rootEntry, level, cnt);
        }

        return outlist;
    }

    private static void searchDescendantEntries(List outlist, DictEntry rootEntry, int level, int count)
    {
        outlist.add(rootEntry);
        count++;
        List children = rootEntry.getChildren();
        if(children == null)
            return;
        if(count > level)
            return;
        for(int j = 0; j < children.size(); j++)
            searchDescendantEntries(outlist, (DictEntry)children.get(j), level, count);

    }
    
    public static List getDictEntries(String dictTypeId, String filterOp, String filterField, String filterStr)
    {
    	DictType dict = getDictType(dictTypeId);
    	return dict.getDictEntries(filterOp, filterField, filterStr);
    }

    public static List getDictEntries(String dictTypeId, String filterOp, String filterField, String filterStr,String roles)
    {
        DictType dict = getDictType(dictTypeId);
        dict.setRoles(roles);
        return dict.getDictEntries(filterOp, filterField, filterStr,roles);
    }
    public static List getDictEntries(String dictTypeId, String filterOp, String filterField, String filterStr, int level)
    {
        List rootEntries = getDictEntries(dictTypeId, filterOp, filterField, filterStr);
        List outlist = new ArrayList();
        if(level == 0 || level < -1)
            return rootEntries;
        if(level == -1)
            level = 0x186a0;
        int cnt = 0;
        for(int i = 0; i < rootEntries.size(); i++)
        {
            DictEntry rootEntry = (DictEntry)rootEntries.get(i);
            searchDescendantEntries(outlist, rootEntry, level, cnt);
        }

        return outlist;
    }
    
    public static List getDictEntries(String dictTypeId, String filterOp, String filterField, String filterStr, int level,String roles)
    {
    	List rootEntries = getDictEntries(dictTypeId, filterOp, filterField, filterStr,roles);
    	List outlist = new ArrayList();
    	if(level == 0 || level < -1)
    		return rootEntries;
    	if(level == -1)
    		level = 0x186a0;
    	int cnt = 0;
    	for(int i = 0; i < rootEntries.size(); i++)
    	{
    		DictEntry rootEntry = (DictEntry)rootEntries.get(i);
    		searchDescendantEntries(outlist, rootEntry, level, cnt);
    	}
    	
    	return outlist;
    }

    public static List getDictEntries(Field field,String roles)
    {
        if(!field.isDict().booleanValue())
            return null;
        String dictTypeId = field.getDictTypeId();
        if(dictTypeId != null)
            return getDictEntries(dictTypeId,roles);
        else
            return null;
    }

    private static boolean reloadDict(DictFactory dictFactory)
    {
        try
        {
            Map types = dictFactory.loadAllDictTypes();
            dictmap.putAll(types);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean reloadAllDict()
    {
        boolean r = true;
        clearDictCache();
        if(useCache)
        {
            dictCache.remove("DICTMAP");
            return true;
        }
        for(Iterator i$ = factoryConfigs.iterator(); i$.hasNext();)
        {
            DictFactoryConfig config = (DictFactoryConfig)i$.next();
            try
            {
                Map types = config.getFactory().loadAllDictTypes();
                dictmap.putAll(types);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                r = false;
            }
        }

        isLoad = true;
        return r;
    }

    public static boolean reloadEosDict()
    {
        String eosDictFactory = "com.eos.server.dict.EOSBusinDictFactory_yzjmwd";
        DictFactory factory;
        try
        {
            factory = (DictFactory)Class.forName(eosDictFactory).newInstance();
        }
        catch(ClassNotFoundException e)
        {
        	eosDictFactory = "com.eos.server.dict.EOSBusinDictFactory_gotop"; 
            try
            {
                factory = (DictFactory)Class.forName(eosDictFactory).newInstance();
            }
            catch(Exception e2)
            {
            	e2.printStackTrace();
            	return false;
            }
        }catch(Exception e){
        	return false;
        }
        return reloadDict(factory);
    }

    public static boolean registerFactory(String factoryName)
    {
        DictFactoryConfig config = addFactory(factoryName);
        if(config == null)
        {
            return false;
        } else
        {
            reloadDict(config.getFactory());
            return true;
        }
    }

    private static DictFactoryConfig addFactory(String factoryName)
    {
        DictFactoryConfig config = new DictFactoryConfig();
        config.setFactoryName(factoryName);
        try
        {
            DictFactory factory = (DictFactory)Class.forName(factoryName).newInstance();
            config.setFactory(factory);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        factoryConfigs.add(config);
        return config;
    }

    public static boolean unregisterFactory(String factoryName)
    {
        for(Iterator i$ = factoryConfigs.iterator(); i$.hasNext();)
        {
            DictFactoryConfig factoryConfig = (DictFactoryConfig)i$.next();
            if(factoryConfig.getFactoryName().equals(factoryName))
            {
                factoryConfigs.remove(factoryConfig);
                return true;
            }
        }

        return false;
    }

    public static void registerDictConfigModelWithoutLoad(DictConfigModel model)
    {
        if(model != null)
        {
            useCache = model.isUseCacher();
            Map factoryMap = model.getFactorys();
            if(factoryMap != null)
            {
                Iterator i$ = factoryMap.values().iterator();
                do
                {
                    if(!i$.hasNext())
                        break;
                    String factory = (String)i$.next();
                    if(!isFactoryLoaded(factory))
                        addFactory(factory);
                } while(true);
            }
        }
    }

    private static boolean isFactoryLoaded(String factoryName)
    {
        for(Iterator i$ = factoryConfigs.iterator(); i$.hasNext();)
        {
            DictFactoryConfig factoryConfig = (DictFactoryConfig)i$.next();
            if(factoryConfig.getFactoryName().equals(factoryName))
                return true;
        }

        return false;
    }

    public static void initDictFactory(com.eos.infra.config.Configuration.Module module)
    {
        com.eos.infra.config.Configuration.Group group = module.getGroup("Dict-Factory");
        String names[] = group.valueNames();
        factoryConfigs.clear();
        String arr$[] = names;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String name = arr$[i$];
            String factoryName = group.getConfigValue(name);
            addFactory(factoryName);
        }

        group = module.getGroup("Cache");
        if(group != null)
        {
            if(group.getConfigValue("UseCache").equals("true"))
                useCache = true;
            String cacheName = group.getConfigValue("CacheName");
            dictCache = CacheFactory.getInstance().findCache(cacheName);
        }
    }

    public static void addDictFactory(DictFactoryConfig config)
    {
        factoryConfigs.add(config);
    }

    public static void clearDictCache()
    {
/*    	System.out.println("---------------111-");
        dictmap.clear();
        dictTypeI18n.clear();
        dictEntryI18n.clear();*/
    }
    public static void clearDict_Cache()
    {
        dictmap.clear();
        dictTypeI18n.clear();
        dictEntryI18n.clear();
    }
    public static void loadDictI18nCache()
    {
        dictmap.put("_EOS_DICT_TYPE_I18N_", dictTypeI18n);
        dictmap.put("_EOS_DICT_ENTRY_I18N_", dictEntryI18n);
    }

    public static Map getDictMap()
    {
        if(useCache)
        {
            Map tmp = (Map)dictCache.get("DICTMAP");
            return tmp;
        }
        if(!isLoad)
            synchronized(loadLock)
            {
                reloadAllDict();
            }
        return dictmap;
    }

    public static Map getDictEntryI18n()
    {
        if(useCache)
            return (Map)((Map)dictCache.get("DICTMAP")).get("_EOS_DICT_ENTRY_I18N_");
        else
            return dictEntryI18n;
    }

    public static void setDictEntryI18n(Map map)
    {
    }

    public static Map getDictTypeI18n()
    {
        if(useCache)
            return (Map)((Map)dictCache.get("DICTMAP")).get("_EOS_DICT_TYPE_I18N_");
        else
            return dictTypeI18n;
    }

    public static void setDictTypeI18n(Map map)
    {
    }

}
