package com.gotop.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.map.ObjectMapper;


public class Struts2Utils {

	//-- header --//
	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	private static final String HEADER_CONTENT_LENGTH = "content-length";
	private static final String DEFAULT_ENCODING = "UTF-8";

	private static final boolean DEFAULT_NOCACHE = true;

	//-- content-type --//
	private static final String TEXT_TYPE = "text/plain";
	private static final String JSON_TYPE = "application/json";
	private static final String XML_TYPE = "text/xml";
	private static final String HTML_TYPE = "text/html";
	private static final String JS_TYPE = "text/javascript";

	private static ObjectMapper mapper = new ObjectMapper();

	//-- ȡ��Request/Response/Session�ļ򻯺��� --//
	/**
	 * HttpSession
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * HttpServletResponse
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * String
	 */
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	//-- �ƹ�jsp/freemakerֱ������ı��ĺ��� --//
	/**
	 * ֱ��������ݵļ�㺯��.

	 * eg.
	 * render("text/plain", "hello", "encoding:GBK");
	 * render("text/plain", "hello", "no-cache:false");
	 * render("text/plain", "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param headers �ɱ��header���飬Ŀǰ���ܵ�ֵΪ"encoding:"��"no-cache:",Ĭ��ֵ�ֱ�ΪUTF-8��true.
	 */
	public static void render(final String contentType, final String content, final String... headers) {
		HttpServletResponse response = initResponse(contentType, headers);
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * ֱ������ı�.
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final String text, final String... headers) {
		render(TEXT_TYPE, text, headers);
	}

	/**
	 * ֱ�����HTML.
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final String html, final String... headers) {
		render(HTML_TYPE, html, headers);
	}

	/**
	 * ֱ�����XML.
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final String xml, final String... headers) {
		render(XML_TYPE, xml, headers);
	}

	/**
	 * ֱ�����JSON.
	 * 
	 * @param jsonString json�ַ�.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final String jsonString, final String... headers) {
		render(JSON_TYPE, jsonString, headers);
	}

	/**
	 * ֱ�����JSON,ʹ��Jacksonת��Java����.
	 * 
	 * @param data ������List<POJO>, POJO[], POJO, Ҳ����Map��ֵ��.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Object data, final String... headers) {
		HttpServletResponse response = initResponse(JSON_TYPE, headers);
		try {
			//System.out.print("reader Json call me");
			mapper.writeValue(response.getWriter(), data);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * ֱ�����֧�ֿ���Mashup��JSONP.
	 * 
	 * @param callbackName callback������.
	 * @param object Java����,������List<POJO>, POJO[], POJO ,Ҳ����Map��ֵ��, ����ת��Ϊjson�ַ�.
	 */
	public static void renderJsonp(final String callbackName, final Object object, final String... headers) {
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}

		String result = new StringBuilder().append(callbackName).append("(").append(jsonString).append(");").toString();

		//��ȾContent-TypeΪjavascript�ķ�������,������Ϊjavascript���, ��callback197("{html:'Hello World!!!'}");
		render(JS_TYPE, result, headers);
	}

	/**
	 * ����������contentType��headers.
	 */
	private static HttpServletResponse initResponse(final String contentType, final String... headers) {
		//����headers����
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		Integer contentLength = 0;
		for (String header : headers) {
			String headerName = StringUtils.substringBefore(header, ":");
			String headerValue = StringUtils.substringAfter(header, ":");
             //如果两个字符串的长度相等，并且两个字符串中的相应字符都相等（忽略大小写）
			if (StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else if (StringUtils.equals(headerName, HEADER_CONTENT_LENGTH)) {
				contentLength = Integer.parseInt(headerValue);
			} else {
				throw new IllegalArgumentException(headerName + "����һ���Ϸ���header����");
			}
		}

		HttpServletResponse response = ServletActionContext.getResponse();

		//����headers����
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		
		if(contentLength>0){
			response.setContentLength(contentLength);
		}

		if (noCache) {
			//WebUtils.setNoCacheHeader(response);
		}

		return response;
	}

}
