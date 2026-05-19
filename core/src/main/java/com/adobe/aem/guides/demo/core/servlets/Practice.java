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
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class,immediate = true)
@SlingServletResourceTypes(resourceTypes = {"bin/meth/vadalara"},
methods = {"GET","PUT","POST","DELETE"},
extensions = {"json","html","txt"},
selectors = {"spin","bowled"})
public class Practice extends SlingAllMethodsServlet { 
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		ResourceResolver resolver = request.getResourceResolver();
		Resource userResource = resolver.getResource("/content/users");

		if(userResource!=null) {
			Iterator<Resource> childUsersList = userResource.listChildren();

			JsonArrayBuilder userJsonList = Json.createArrayBuilder();
			while (childUsersList.hasNext()) {
				Resource resource = (Resource) childUsersList.next();

				ValueMap properties = resource.getValueMap();
				String firstName = properties.get("firstName", String.class);
				String lastName = properties.get("lastName", String.class);
				String email = properties.get("email", String.class);
				String phone = properties.get("phone", String.class);

				JsonObjectBuilder userJson = Json.createObjectBuilder();
				userJson.add("firstName", firstName);
				userJson.add("lastName", lastName);
				userJson.add("email", email);
				userJson.add("phone", phone);

				userJsonList.add(userJson);

			}
			response.getWriter().write(userJsonList.build().toString());
		}
			

	}
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		ResourceResolver resolver = request.getResourceResolver();
		Resource userResource = resolver.getResource("/content/users");
		
		String userId = request.getParameter("userId");
		
		Map<String,Object> properties = new HashMap<>();
		properties.put("firstName",request.getParameter("firstName"));//to read the firstName from the request object by using request.getParameter("firstName"));
		properties.put("lastName",request.getParameter("lastName"));
		properties.put("email",request.getParameter("email"));
		properties.put("phone",request.getParameter("phone"));
		
		resolver.create(userResource, userId, properties);
		resolver.commit();
		resolver.close();
		response.getWriter().write("User Sucessfully created.."+userId);
	}
	
	
	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = request.getParameter("userId");

		ResourceResolver resolver = request.getResourceResolver();
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
				if(phone!=null) {
					mProp.put("phone", phone);
				}
				
				resolver.commit();
				resolver.close();
				response.getWriter().write("User Info is Updated..."+userId);
		}
		
		
	}
	
	protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = request.getParameter("userId");

		ResourceResolver resolver = request.getResourceResolver();
		Resource userResource = resolver.getResource("/content/users"+userId);
		resolver.delete(userResource);
		resolver.commit();
		resolver.close();
		response.getWriter().write("User Deleted Successfully.."+userId);
	}
}
