package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

@Component(service = Servlet.class,
		immediate = true)
@SlingServletPaths(value="/bin/Demo/dinesh/v1")
public class ArticleInfoServelet extends SlingAllMethodsServlet {

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		ResourceResolver resolver = request.getResourceResolver();
		 PageManager pageManager = resolver.adaptTo(PageManager.class);
		 
		 Page articlePage = pageManager.getPage("/content/demo/us/en");
		 Iterator<Page> childPages = articlePage.listChildren();
		 JsonArrayBuilder pageListJson = Json.createArrayBuilder();
		 while(childPages.hasNext()) {
			 Page page = (Page) childPages.next();
			 JsonObjectBuilder pageJson = Json.createObjectBuilder();
			 pageJson.add("title",page.getTitle());
			 pageJson.add("path",page.getPath());
			 pageListJson.add(pageJson);
			 
		 }
		 try {
				pageManager.create("/content/Demo/us/en",
						"article-20",
						"/conf/Demo/settings/wcm/templates/sai-temp",
						"Article-20");
			} catch (WCMException e) {
				
				e.printStackTrace();
			}
			
			resolver.commit();
			resolver.close();
	}
}
