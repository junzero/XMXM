package com.gotop.util.jdbc;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.beans.DirectFieldAccessor;

public class PropertyAccessorFactory {


	  public PropertyAccessorFactory() {
	  }

	  static public BeanWrapper forBeanPropertyAccess(Object target) {
	    return new BeanWrapperImpl(target);
	  }

	  static public ConfigurablePropertyAccessor forDirectFieldAccess(Object target) {
	    return new DirectFieldAccessor(target);
	  }
}
