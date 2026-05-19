package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
@Component(service = Servlet.class,immediate = true)
@SlingServletPaths("/bin/hi/man")
public class Task5P extends SlingAllMethodsServlet {
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
			String outPut = "";
			if(request.getParameter("Number")!=null) {
				outPut = RandomStringUtils.randomAlphanumeric(6);
			}else if(request.getParameter("Letter")!=null) {
				outPut = RandomStringUtils.randomAlphabetic(6);
			}else if(request.getParameter("Random")!=null) {
				outPut = m1(3,3);
			}else {
				outPut="enter Number,Letter,Random";
			}
			response.setContentType("text/plain");
			response.getWriter().write(outPut);
	}
	
	private String m1(int numberCount, int letterCount) {
		String letters = RandomStringUtils.randomAlphabetic(letterCount).toUpperCase();
		String numbers = RandomStringUtils.randomAlphanumeric(numberCount);
		
		List<Character> list = new ArrayList<Character>();
		for(char c : letters.toCharArray()) {
			list.add(c);
		}
		for(char c : numbers.toCharArray()) {
			list.add(c);
		}
		Collections.shuffle(list);
		StringBuilder obj = new StringBuilder();
		for(char c : list) {
			obj.append(c);
		}
		return obj.toString();
	}

}
