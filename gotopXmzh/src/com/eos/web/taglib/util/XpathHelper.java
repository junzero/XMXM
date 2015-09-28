 package com.eos.web.taglib.util;
 
 import com.primeton.ext.data.sdo.DataUtil;
 import commonj.sdo.DataObject;
 import commonj.sdo.Property;
 import commonj.sdo.Type;
 import java.lang.reflect.Array;
 import java.lang.reflect.InvocationTargetException;
 import java.lang.reflect.Method;
 import java.lang.reflect.Modifier;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import org.w3c.dom.Node;
 import org.w3c.dom.NodeList;
 
 public class XpathHelper
 {
   public static final int START = 1;
   private Set<Object> cache = new HashSet();
 
   private void clearCache()
   {
     this.cache.clear();
   }
 
   private void putCache(Object obj)
   {
     this.cache.add(obj);
   }
 
   private boolean isUsed(Object obj)
   {
     return this.cache.contains(obj);
   }
 
   public static Map<String, Object> Object2Map(Object obj, String xpath)
   {
     XpathHelper parser = new XpathHelper();
     parser.clearCache();
     return parser.parseObject(obj, xpath);
   }
 
   private Object[] toObjArray(Object obj)
   {
     if (obj == null) {
       return null;
     }
     int len = Array.getLength(obj);
     Object[] objs = new Object[len];
     for (int i = 0; i < len; i++) {
       objs[i] = Array.get(obj, i);
     }
     return objs;
   }
 
   private Map<String, Object> parseObject(Object obj, String xpath)
   {
     Map map = new HashMap();
     if (obj == null) {
       return map;
     }
     Class cls = obj.getClass();
     if (xpath == null) {
       xpath = cls.getSimpleName();
     }
     if (DataUtil.isPrimitiveObject(cls)) {
       map.put(xpath, obj);
       return map;
     }
     if (isUsed(obj)) {
       return map;
     }
     putCache(obj);
 
     if (cls.isArray())
       return parseArray(toObjArray(obj), xpath);
     if ((obj instanceof Iterable))
       return parseIterable((Iterable)obj, xpath);
     if ((obj instanceof Map))
       return parseMap((Map)obj, xpath);
     if ((obj instanceof DataObject))
       return parseDataObject((DataObject)obj, xpath);
     if ((obj instanceof Node)) {
       return parseElement((Node)obj, xpath);
     }
     return parseJavaBean(obj, xpath);
   }
 
   private Map<String, Object> parseIterable(Iterable iterate, String xpath)
   {
     Map map = new HashMap();
     String tempXpath = null;
     int i = 0;
     for (Iterator i$ = iterate.iterator(); i$.hasNext(); ) { Object obj = i$.next();
       tempXpath = xpath + "[" + i + "]";
       map.putAll(parseObject(obj, tempXpath));
       i++;
     }
     return map;
   }
 
   private Map<String, Object> parseArray(Object[] objArr, String xpath)
   {
     Map map = new HashMap();
     String tempXpath = null;
     int i = 0;
     for (Object obj : objArr) {
       tempXpath = xpath + "[" + i + "]";
       map.putAll(parseObject(obj, tempXpath));
       i++;
     }
     return map;
   }
 
   private Map<String, Object> parseMap(Map mapObj, String xpath)
   {
     Map map = new HashMap();
     Set<Map.Entry> entrySet = mapObj.entrySet();
     String tempXpath = null;
     for (Map.Entry entry : entrySet) {
       tempXpath = buildXpath(xpath, (String)entry.getKey());
       map.putAll(parseObject(entry.getValue(), tempXpath));
     }
     return map;
   }
 
   private Map<String, Object> parseDataObject(DataObject dataObj, String xpath)
   {
     Map map = new HashMap();
     String type = dataObj.getType().getURI();
     map.put(buildXpath(xpath, "__type"), "sdo:" + type);
     List<Property> props = dataObj.getInstanceProperties();
     String tempXpath = null;
     for (Property prop : props) {
       tempXpath = buildXpath(xpath, prop.getName());
       if (dataObj.get(prop.getName()) == null) {
         if (dataObj.isSet(prop.getName()))
           map.putAll(parseObject("", tempXpath));
       }
       else map.putAll(parseObject(dataObj.get(prop.getName()), tempXpath));
     }
     return map;
   }
 
   private Map<String, Object> parseJavaBean(Object obj, String xpath)
   {
     Map map = new HashMap();
     String type = obj.getClass().toString();
     map.put(buildXpath(xpath, "__type"), "java:" + type);
     Method[] methods = obj.getClass().getDeclaredMethods();
     Object[] params = new Object[0];
     String tempXpath = null;
     for (Method method : methods)
     {
       if ((Modifier.isStatic(method.getModifiers())) || (Modifier.isPrivate(method.getModifiers()))) {
         continue;
       }
       String methodName = method.getName();
       Object value = null;
       if (((!methodName.startsWith("get")) && (!methodName.startsWith("is"))) || (method.getParameterTypes().length != 0)) continue;
       try {
         value = method.invoke(obj, params);
       }
       catch (IllegalArgumentException e) {
         e.printStackTrace();
       }
       catch (IllegalAccessException e) {
         e.printStackTrace();
       }
       catch (InvocationTargetException e) {
         e.printStackTrace();
       }
       if (value != null) {
         if (methodName.startsWith("get")) {
           methodName = methodName.substring(3);
         }
         if (methodName.startsWith("is")) {
           methodName = methodName.substring(2);
         }
         char firstChar = methodName.charAt(0);
         char secondChar = methodName.charAt(1);
         if (Character.isLowerCase(secondChar)) {
           firstChar = Character.toLowerCase(firstChar);
         }
         methodName = firstChar + methodName.substring(1);
         tempXpath = buildXpath(xpath, methodName);
         map.putAll(parseObject(value, tempXpath));
       }
     }
 
     return map;
   }
 
   private Map<String, Object> parseElement(Node nodeObj, String xpath)
   {
     Map map = new HashMap();
     if (nodeObj.getNodeType() == 3) {
       map.put(xpath, nodeObj.getNodeValue());
     } else if ((nodeObj.getNodeType() == 1) || (nodeObj.getNodeType() == 9)) {
       NodeList nodes = nodeObj.getChildNodes();
       String tempXpath = null;
       Map tagNameMap = new HashMap();
       Map indexMap = new HashMap();
       for (int i = 0; i < nodes.getLength(); i++) {
         Node node = nodes.item(i);
         String tagName = node.getNodeName();
         if (tagNameMap.containsKey(tagName)) {
           int tagIndex = ((Integer)tagNameMap.get(tagName)).intValue();
           tagNameMap.put(tagName, Integer.valueOf(tagIndex + 1));
           indexMap.put(tagName, Integer.valueOf(1));
         } else {
           tagNameMap.put(tagName, Integer.valueOf(1));
         }
       }
 
       for (int i = 0; i < nodes.getLength(); i++) {
         Node node = nodes.item(i);
         if (node.getNodeType() == 3) {
           map.put(xpath, node.getNodeValue());
         } else {
           String tagName = node.getNodeName();
           if (((Integer)tagNameMap.get(tagName)).intValue() > 1) {
             int index = ((Integer)indexMap.get(tagName)).intValue();
             tempXpath = buildXpath(xpath, node.getNodeName()) + "[" + index + "]";
             indexMap.put(tagName, Integer.valueOf(index + 1));
           } else {
             tempXpath = buildXpath(xpath, node.getNodeName());
           }
           map.putAll(parseObject(node, tempXpath));
         }
       }
     }
     return map;
   }
 
   private String buildXpath(String parentXpath, String childXpath)
   {
     if (!parentXpath.endsWith("/")) {
       parentXpath = parentXpath + "/";
     }
     if (childXpath.startsWith("/")) {
       childXpath = childXpath.substring(1);
     }
     return parentXpath + childXpath;
   }
 }