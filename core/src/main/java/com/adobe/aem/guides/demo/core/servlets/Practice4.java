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
public class Practice4 extends SlingAllMethodsServlet{
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		ResourceResolver resolver = request.getResourceResolver();
		Resource userResource = resolver.getResource("/content/users");
		if(userResource!=null) {
			Iterator<Resource> resource = userResource.listChildren();
			JsonArrayBuilder userJsonList = Json.createArrayBuilder();
			while(resource.hasNext()) {
				Resource userResourceChild = (Resource)resource.next();

				ValueMap propeties = userResourceChild.getValueMap();
				String firstName = propeties.get("firstName", String.class);
				String lastName = propeties.get("lastName", String.class);
				String email = propeties.get("email", String.class);
				String phone = propeties.get("phone", String.class);

				JsonObjectBuilder userJson = Json.createObjectBuilder();
				userJson.add("firstName",firstName );
				userJson.add("lastName",lastName );
				userJson.add("email",email );
				userJson.add("phone",phone );

				userJsonList.add(userJson);
			}
			PageManager page = resolver.adaptTo(PageManager.class);
			try {
				page.create("/content/Demo/us/en/articles",
						"meth-Vadalara",
						"/conf/Demo/settings/wcm/templates/sai-temp",
						"Meth-Vadulu");
			} catch (WCMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().write("Data coming from server-GET");
		}
		resolver.commit();
		resolver.close();
	}
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		ResourceResolver resolver = request.getResourceResolver();
		Resource userResource = resolver.getResource("/content/users"+userId);
		if(userResource!=null) {
			Map<String,Object> mProp = new HashMap<String, Object>();
			mProp.put("firstName", request.getParameter("firstName"));
			mProp.put("lastName", request.getParameter("lastName"));
			mProp.put("email", request.getParameter("email"));
			mProp.put("phone", request.getParameter("phone"));
			resolver.create(userResource, userId, mProp);
			response.getWriter().write("New-User Data Created-POST");
			resolver.commit();
			resolver.close();
		}
	}
	@Override
	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		ResourceResolver resolver = request.getResourceResolver();
		Resource userResource = resolver.getResource("/content/users"+userId);
		if(userResource!=null) {
			ModifiableValueMap map = resolver.adaptTo(ModifiableValueMap.class);
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			if(firstName!=null) {
				map.put("firstName", firstName);
			}
			if(lastName!=null) {
				map.put("lastName", lastName);
			}
			if(email!=null) {
				map.put("email", email);
			}
			if(phone!=null) {
				map.put("phone", phone);
			}
			resolver.commit();
			resolver.close();
			response.getWriter().write("Updated Successfully-PUT");
		}
	}
	@Override
	protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		ResourceResolver resolver = request.getResourceResolver();
		Resource userResource = resolver.getResource("/content/users"+userId);
		resolver.delete(userResource);
		resolver.commit();
		resolver.close();
		response.getWriter().write("Deleted Successfully-DELETE");
	}
}
