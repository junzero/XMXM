package com.gotop.util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.io.xml.DomDriver;
@XStreamAlias("class")
public class ClassesTest {
	/*     * 设置属性显示     */
	@XStreamAsAttribute
	@XStreamAlias("名称")
	private String name;
	/*     * 忽略     */
	@XStreamOmitField
	private int number;
	@XStreamImplicit(itemFieldName = "Students")
	private List<Student> students;
	@SuppressWarnings("unused")
	
	@XStreamConverter(SingleValueCalendarConverter.class)
	private Calendar created = new GregorianCalendar();
	
	@XStreamConverter(SingleValueDateConverter.class)
	private Date testDate = new Date();

	public ClassesTest() {
	}

	public ClassesTest(String name, Student... stu) {
		this.name = name;
		this.students = Arrays.asList(stu);
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	
	public static void main(String[] arg){
		ClassesTest ct = new ClassesTest();
		ct.setTestDate(new Date());
		XStream sm = new XStream(new DomDriver()); //创建Xstream对象 
		sm.autodetectAnnotations(true);
		String smxml = sm.toXML(ct);
		System.out.println(smxml);
	}
}