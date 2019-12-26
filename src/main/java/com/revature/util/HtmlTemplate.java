package com.revature.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/*
 * Convenience helper class to facilitate the creation of HTML documents from Java more easily
 */
public class HtmlTemplate {
	public static void goBack(PrintWriter out) {
		out.println("<hr><input type='button' value='BACK' "
				+ "onclick='goBack()'>"
				+ "<script>"
				+ "function goBack(){ window.history.back();}"
				+ "</script>");
	}
	
	public static PrintWriter getHtmlWriter(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		return resp.getWriter();
	}
	 
	//...headers as a parameter is known as varargs, which stands for variable arguments
	// This means, with one method signature, I can support any number of parameters
	// You can only have 1 varargs in a method signature, and support only one type
	public static void printTableHeaders(PrintWriter out, String ...headers) {
		out.println("<table border=2px><tr>");
		for(String h: headers) {
			out.println("<th>" + h + "/th>");
		}
		out.println("</tr>");	
	}
	
	
}
