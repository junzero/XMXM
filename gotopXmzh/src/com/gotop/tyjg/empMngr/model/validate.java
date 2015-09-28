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
 * Created on 2011-4-22
 *******************************************************************************/


package com.gotop.tyjg.empMngr.model;


import com.eos.system.annotation.Bizlet;

/**
 * TODO fill class info here
 *
 * @author Administrator
 * @date 2011-04-22 17:25:32
 */
/*
 * Modify history
 * $Log$ 
 */
@Bizlet("")
public class validate {
	/**
	 *Wi��ֵΪ��Math.pow(2,18-i) mod 11  i��1��17֮��
	 */
	 private final int Wi[]={7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};

	/**
	 *lastNumber��Ԥ�����úõ�
	 */
	 private final int lastNumber[]={1,0,'X',9,8,7,6,5,4,3,2};
	/**
	 *ǰ17Ϊ���֤�����: ��(Ai��Wi)
	 *@param id 18λ���֤����
	 */

	 private  int sum(String id){
	  int sum=0;
	  for(int i=0;i<17;i++){
	   String id_number=id.substring(i,i+1);
	   sum=sum+Integer.parseInt(id_number)*Wi[i];
	  }
	  return sum;
	 }
	/**
	 *ȷ��18λ���֤�����Ƿ�Ϸ�
	 *@param id 18λ���֤����
	 */
	 private boolean vertifyLastNumber(String id){
	  int temp=sum(id)%11;
	  return String.valueOf(lastNumber[temp]).equals(id.substring(17));
	 }
	/**
	 *�ж����֤����λ��,ȷ�����֤�Ƿ�Ϸ���������֤��15λ����ʾ��18λ��ĺ���
	 *@param inputNumber ��������֤����
	 */
	 @Bizlet("")
	 public boolean vertifyId(String inputNumber){
	  boolean flag=false;;
	  int length=inputNumber.length();
	    if(length==15){
	  // System.out.println("���֤������18λΪ��"+upperToEighteen(inputNumber));
	   flag = vertifyLastNumber(upperToEighteen(inputNumber));
	  }else if(length==18){
	   flag=vertifyLastNumber(inputNumber);
	  }//else{
	   //System.out.println("���֤�������λ15λ����18λ��");
	 // }
	  return flag;
	 }
	/**
	 *��úϷ�18λ���֤�������һλ
	 *@param id 17λ���֤����
	 */

	 private String getLastNumber(String id){
	  return String.valueOf(lastNumber[sum(id)%11]);
	 }
	/**
	 *��15λ���֤������18λ
	 *@param fifteenId 15λ���֤����
	 */
	 @Bizlet("")
	 public String upperToEighteen(String fifteenId){
	  String id=fifteenId.substring(0,6)+"19"+fifteenId.substring(6,15);
	  return id+getLastNumber(id);
	 }


}
