package com.gotop.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {
    private String targetEncoding = "ISO8859-1";
    public void init(FilterConfig config) throws ServletException {
        this.targetEncoding = config.getInitParameter("encoding");
    }
     public  void doFilter(ServletRequest srequest, ServletResponse  sresponse, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)srequest;
        HttpServletResponse response = (HttpServletResponse)sresponse;
        request.setCharacterEncoding(targetEncoding);
        response.setCharacterEncoding(targetEncoding);
        response.setContentType("text/html;charset="+targetEncoding);
        chain.doFilter(srequest,sresponse);
    }
    public void destroy() {
    }	
}