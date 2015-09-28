package com.gotop.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletUtil {

	public static void forward(HttpServletRequest req,HttpServletResponse resp,String url){
		RequestDispatcher rd = req.getRequestDispatcher(url);	
		try {
			rd.forward(req,resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
