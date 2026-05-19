package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

@Component(service = Servlet.class,immediate = true)
@SlingServletPaths(value = {"/bin/hi/hello"})
public class Practice0 extends SlingAllMethodsServlet{
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		ResourceResolver resolver = request.getResourceResolver();
		Resource userResource = resolver.getResource("/content/users");
		if(userResource!=null) {
			Iterator<Resource>userResourceList = userResource.listChildren();
			JsonArrayBuilder userJsonList = Json.createArrayBuilder();
			while (userResourceList.hasNext()) {
				Resource resource = (Resource) userResourceList.next();
				ValueMap properties = resource.getValueMap();
				String firstName = properties.get("firstName",String.class);
				String lastName = properties.get("lastName",String.class);
				String email = properties.get("email",String.class);
				String phone = properties.get("phone",String.class);

				JsonObjectBuilder userJson =	Json.createObjectBuilder();
				userJson.add("firstName", firstName);
				userJson.add("lastName", lastName);
				userJson.add("email", email);
				userJson.add("phone", phone);
				userJsonList.add(userJson);
			}
			PageManager page = userResource.adaptTo(PageManager.class);
			try {
				page.create("/content/Demo/us/en/articles", "maheshPage", "/conf/Demo/settings/wcm/templates/sai-temp", "MaheshPage");
			} catch (WCMException e) {
				
				e.printStackTrace();
			}
		}		response.getWriter().write("data coming from the server-GET");

	}@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		try(ResourceResolver resolver = request.getResourceResolver();){
			Resource userResource = resolver.getResource("/content/users"+userId);
			if(userResource!=null) {
				Map<String, Object> properties =new HashMap<String, Object>();
				properties.put("firstName", request.getParameter("firstName"));
				properties.put("lastName", request.getParameter("lastName"));
				properties.put("email", request.getParameter("email"));
				properties.put("phone", request.getParameter("phone"));
				resolver.commit();
				resolver.close();
				resolver.create(userResource, userId, properties);
			 	response.getWriter().write("new user Created Successfully..."+userId);
			}

		}
	}
	@Override
	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
	 	String userId = request.getParameter("userId");
		try(ResourceResolver resolver = request.getResourceResolver()){
			Resource userResource = resolver.getResource("/content/users"+userId);
			if(userResource!=null) {
			  	ModifiableValueMap mProp = userResource.adaptTo(ModifiableValueMap.class);
			 	String firstName = request.getParameter("firstName");
			 	String lastName = request.getParameter("lastName");
			 	String email = request.getParameter("email");
			 	String phone = request.getParameter("phone");
			 	
			 	if(firstName!=null) {
			 		mProp.put("firstName", firstName);
			 	}
			 	if(lastName!=null) {
			 		mProp.put("lastName", lastName);
			 	}
			 	if(email!=null) {
			 		mProp.put("email", email);
			 	}
			 	if(firstName!=null) {
			 		mProp.put("phone", phone);
			 	}
			 	resolver.commit();
			 	resolver.close();
			 	response.getWriter().write("");
			}
		}
	}
	@Override
	protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		try(ResourceResolver resolver = request.getResourceResolver();){
			Resource userResource = resolver.getResource("/content/users"+userId);
			resolver.delete(userResource);
			resolver.commit();
			resolver.close();
		 	response.getWriter().write("user Deleted Successfully..."+userId);
		}
		
		
	}
	
}
