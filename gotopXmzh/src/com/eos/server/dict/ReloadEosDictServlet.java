package com.eos.server.dict;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReloadEosDictServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "text/xml; charset=UTF-8";

	public void init() throws ServletException {
//		DictManager.reloadEosDict();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DictManager.reloadEosDict();
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print("<root><data><reCode>1</reCode></data></root>");
		writer.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// Clean up resources
	public void destroy() {
	}
}
