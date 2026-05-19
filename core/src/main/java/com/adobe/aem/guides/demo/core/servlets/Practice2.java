package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

@Component(service=Servlet.class,immediate = true)
@SlingServletPaths(value="/bin/Demo/dinesh/v1123")
public class Practice2 extends SlingAllMethodsServlet {
@Override
protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
		throws ServletException, IOException {
	ResourceResolver resolver = request.getResourceResolver();
	PageManager pageManager = resolver.adaptTo(PageManager.class);
	try {
		pageManager.create("/content/Demo/us/en/articles",
				"meth-Vadulu",
				"/conf/Demo/settings/wcm/templates/sai-temp",
				"Meth-Vadulu");
	} catch (WCMException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	resolver.commit();
	resolver.close();
	
}
}
