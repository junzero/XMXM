package com.primeton.data.xpath.impl.accessor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Map;

import net.sf.cglib.beans.BeanCopier;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.eos.data.xpath.XPathLocatorException;
import com.primeton.data.xpath.impl.XPathContextImpl;
import com.primeton.ext.data.common.BasicTypeHelper;
import com.primeton.ext.data.common.ClassHelper;
import com.primeton.ext.data.xpath.Beans;
import com.primeton.ext.data.xpath.ObjectAccessor;
import com.primeton.ext.data.xpath.ObjectCreatorHelper;
import com.primeton.ext.data.xpath.XPathContext;

public class BeanPropertyAccessor extends AbstractObjectAccessor
  implements ObjectAccessor
{
  public boolean accept(Class parentClass)
  {
    return ClassHelper.getDefaultConstrutor(parentClass) != null;
  }

  private String fixPropertyName(String propertyName)
  {
    if (propertyName == null) {
      return propertyName;
    }
    if (propertyName.length() == 1) {
      if ((propertyName.charAt(0) >= 'A') && (propertyName.charAt(0) <= 'Z'))
        return propertyName.toLowerCase();
      return propertyName;
    }
    if ((propertyName.charAt(1) >= 'A') && (propertyName.charAt(1) <= 'Z')) {
      propertyName = Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1);
    }

    return propertyName;
  }

  private boolean supportedByteCodeGenerate(Class clazz) {
    if (clazz.getName().startsWith("java."))
    {
      return false;
    }

    return !Proxy.class.isAssignableFrom(clazz);
  }

  public Object getPropertyValue(XPathContext context, Object parentValue, String propertyName)
  {
    Object result = null;
    try {
      propertyName = fixPropertyName(propertyName);
//-------------------------------------------------------改造方法----------------------------------------------------------  
//      if (!supportedByteCodeGenerate(parentValue.getClass()))
      if(1==1){
//-------------------------------------------------------改造结束---------------------------------------------------------- 
        result = PropertyUtils.getProperty(parentValue, propertyName);
      }else {
        result = Beans.getValue(parentValue, propertyName);
      }

      com.primeton.ext.data.xpath.Type genericType = getTypeFromGenericType(parentValue.getClass(), propertyName);
      ((XPathContextImpl)context).setValueType(context.getCurrentPointer(), genericType);
    }
    catch (Exception e)
    {
      return null;
    }
    return result;
  }

  public boolean propertyIsExist(XPathContext context, Object parentValue, String propertyName)
  {
    try
    {
      propertyName = fixPropertyName(propertyName);
      return PropertyUtils.getPropertyDescriptor(parentValue, propertyName) != null; 
    } catch (Exception e) {
    	throw new XPathLocatorException(e);
    }
  }

  public void setPropertyValue(XPathContext context, Object parentValue, String propertyName, Object propertyValue)
  {
    Class valueClass = null;
    propertyName = fixPropertyName(propertyName);
    try {
      if (!supportedByteCodeGenerate(parentValue.getClass())) {
        PropertyDescriptor desc = PropertyUtils.getPropertyDescriptor(parentValue, propertyName);

        valueClass = desc.getPropertyType();
      } else {
        valueClass = Beans.getType(parentValue.getClass(), propertyName);
      }
    }
    catch (Exception e) {
      if (isLenient(context)) {
        return;
      }
      throw new XPathLocatorException("property '" + propertyName + "' does not exist or it's setter not accessed!", e);
    }

    if (ClassHelper.isBasicType(valueClass)) {
      propertyValue = BasicTypeHelper.conversion(valueClass, propertyValue);
    }

    if (isLenient(context))
      try {
        if (!supportedByteCodeGenerate(parentValue.getClass()))
          BeanUtils.setProperty(parentValue, propertyName, propertyValue);
        else
          Beans.setValue(parentValue, propertyName, propertyValue);
      }
      catch (Exception e)
      {
      }
    else
      try {
        if (!supportedByteCodeGenerate(parentValue.getClass())) {
          PropertyUtils.setProperty(parentValue, propertyName, propertyValue);
        }
        else
          Beans.setValue(parentValue, propertyName, propertyValue);
      }
      catch (Exception e) {
        throw new XPathLocatorException("cannot set '" + propertyName + "' value!", e);
      }
  }

  public Object createPropertyValue(XPathContext context, Object parentValue, String propertyName)
  {
    if (parentValue == null) {
      throw new XPathLocatorException("the '" + context.getXPath(context.getCurrentPointer()) + "' cannot operate,cause its parent is null!");
    }

    Object result = null;
    try {
      Class parentClass = parentValue.getClass();
      Field field = getField(parentClass, propertyName);
      if (field == null) {
        throw new NoSuchFieldException(propertyName);
      }
      Class propertyClass = field.getType();
      com.primeton.ext.data.xpath.Type genericType = null;
      if ((Collection.class.isAssignableFrom(propertyClass)) || (Map.class.isAssignableFrom(propertyClass)))
      {
        genericType = getTypeFromGenericType(parentClass, propertyName, field, propertyClass);

        ((XPathContextImpl)context).setValueType(context.getCurrentPointer(), genericType);
      }

      com.primeton.ext.data.xpath.Type type = ObjectCreatorHelper.getImplementationType(context, context.getCurrentPointer(), propertyName, propertyClass);

      if (type == null) {
        result = ObjectCreatorHelper.createObject(genericType, context);
      } else {
        if (type.isDefinedByAnnotation()) {
          ((XPathContextImpl)context).setValueType(context.getCurrentPointer(), genericType);
        }

        result = ObjectCreatorHelper.createObject(type, context);
      }
    } catch (Exception e) {
      throw new XPathLocatorException(e.toString(), e);
    }
    return result;
  }

  private static Field getField(Class clazz, String name)
  {
    Field res = null;
    try
    {
      res = clazz.getDeclaredField(name);
    } catch (Exception ignore) {
    }
    if (res != null) {
      return res;
    }

    if (!clazz.isInterface()) {
      Class c = clazz.getSuperclass();
      if ((c != null) && 
        ((res = getField(c, name)) != null)) {
        return res;
      }
    }

    return null;
  }

  private static com.primeton.ext.data.xpath.Type getTypeFromGenericType(Class parentClass, String propertyName)
  {
    try {
      Field field = getField(parentClass, propertyName);
      Class propertyClass = field.getType();
      if ((Collection.class.isAssignableFrom(propertyClass)) || (Map.class.isAssignableFrom(propertyClass)))
      {
        return getTypeFromGenericType(parentClass, propertyName, field, propertyClass);
      }
    }
    catch (Exception e)
    {
    }
    return null;
  }

  private static com.primeton.ext.data.xpath.Type getTypeFromGenericType(Class parentClass, String propertyName, Field field, Class objectClass)
  {
    try {
      java.lang.reflect.Type type = field.getGenericType();
      if ((type != null) && ((type instanceof ParameterizedType))) {
        com.primeton.ext.data.xpath.Type mappingType = new com.primeton.ext.data.xpath.Type(type.toString());
        if ((mappingType.isElement()) || (mappingType.isDocument())) {
          mappingType.setTypeImpl(propertyName);
        }
        return mappingType;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void deletePropertyValue(XPathContext context, Object parentValue, String propertyName)
  {
    propertyName = fixPropertyName(propertyName);
    Object value = null;
    try {
      Class propertyClass = PropertyUtils.getPropertyDescriptor(parentValue, propertyName).getPropertyType();
      if (Character.TYPE == propertyClass) {
        value = Character.valueOf('\000');
      }

    }
    catch (Exception e)
    {
    }

    try
    {
      PropertyUtils.setProperty(parentValue, propertyName, value);
    } catch (Exception e) {
      try {
        BeanUtils.setProperty(parentValue, propertyName, value);
      }
      catch (Exception e1)
      {
      }
    }
  }
}