package com.gotop.util.syslog;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
/**
 * 后置通知
 */
public class LogAfterAdvice implements AfterReturningAdvice{
 
 private static Logger log=Logger.getLogger(LogAfterAdvice.class);
 /**
  * 实现接口方法
  * arg0 返回值，只读
  * arg1 目标方法
  * arg2  目标方法参数
  * arg3  目标对象
  */
 public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
  System.out.println("注册成功！！");
  log.info("注册成功！！"+"\t"+arg1.getName()+"\t"+arg3.getClass().getName());
 }
}
