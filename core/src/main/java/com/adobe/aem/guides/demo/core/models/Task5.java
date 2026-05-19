package com.adobe.aem.guides.demo.core.models;
/*http://localhost:4502/bin/oxigen/task-5?Number=1     => 583027
http://localhost:4502/bin/oxigen/task-5?Letters=1    => XFDJAS
http://localhost:4502/bin/oxigen/task-5?Random=1     => A9B3J2 (shuffled mix)
*/
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
@SlingServletPaths("/bin/oxigen/task-5")
public class Task5 extends SlingAllMethodsServlet{
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		

			String outPut ="";
			if(request.getParameter("Number")!=null) {
				outPut = RandomStringUtils.randomAlphanumeric(6);
			}else if(request.getParameter("Letters")!=null) {
				outPut = RandomStringUtils.randomAlphabetic(6).toUpperCase();
			}else if(request.getParameter("Random")!=null) {
				outPut = generateShuffledRandomString(3,3);
			}else {
				 outPut = "Please provide a valid parameter: Number, Letters, or Random.";
			}
			response.setContentType("text/plain");
			 response.getWriter().write(outPut);
		
		
	}
	private String generateShuffledRandomString(int digitCount, int letterCount) {
		
		String numbers = RandomStringUtils.randomAlphabetic(letterCount).toUpperCase();
		String letters = RandomStringUtils.randomAlphanumeric(digitCount);
		
		List<Character> combine = new ArrayList<>();
		for(char c : numbers.toCharArray()) {
			combine.add(c);
		}
		for(char c : letters.toCharArray()) {
			combine.add(c);
		}
		Collections.shuffle(combine);
		
		StringBuilder result = new StringBuilder();
		for(char c : combine) {
			result.append(c);
		}
		return result.toString(); 
	}
}
