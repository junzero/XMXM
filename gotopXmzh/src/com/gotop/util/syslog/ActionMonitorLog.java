package com.gotop.util.syslog;

import org.aspectj.lang.JoinPoint;
import org.apache.log4j.Logger;

public class ActionMonitorLog {
    protected static Logger log = Logger.getLogger(ActionMonitorLog.class);
    //在类里面写方法，方法名里可以任意的。此处我用标准的before和after来表示
    //此处的JoinPoint类可以获取，action所有的相关配置信息和request等内置对象。
  public void before(JoinPoint joinpoint){
	  log.debug(joinpoint.getTarget().toString()+"-s-"+joinpoint.toShortString());
  }
  public void after(JoinPoint joinpoint){
	  log.debug(joinpoint.getTarget().toString()+"-e-"+joinpoint.toShortString());;
  }
}
