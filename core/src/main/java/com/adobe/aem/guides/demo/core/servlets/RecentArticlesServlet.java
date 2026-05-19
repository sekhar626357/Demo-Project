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
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import com.day.cq.wcm.designimporter.parser.ModifiableHTMLContent;
@Component(service=Servlet.class,immediate = true)
@SlingServletPaths(value="/bin/Demo/dinesh")
public class RecentArticlesServlet extends SlingAllMethodsServlet{

	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		ResourceResolver resolver = request.getResourceResolver();
		Resource userResource = resolver.getResource("/content/users");
		if(userResource!=null) {
			Iterator<Resource> childUsersList = userResource.listChildren();
			JsonArrayBuilder userJsonList = Json.createArrayBuilder();
			while(childUsersList.hasNext()) {
				Resource childUserResource = (Resource)childUsersList.next();
				ValueMap properties = childUserResource.getValueMap();

				String firstName = properties.get("firstName", String.class);
				String lastName = properties.get("lastName", String.class);
				String email = properties.get("email", String.class);
				String phone = properties.get("phone", String.class);

				JsonObjectBuilder userJson = Json.createObjectBuilder();//{firstName:chamu}
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
		properties.put("firstName", request.getParameter("firstName"));
		properties.put("lastName", request.getParameter("lastName"));
		properties.put("email", request.getParameter("email"));
		properties.put("phone", request.getParameter("phone"));


		resolver.create(userResource,userId, properties);
		/*we have to commit the changes otherwise it wont store in the jcr repository*/
		resolver.commit();


		response.getWriter().write("User Successfully created.. "+userId);
	}

	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		ResourceResolver resolver = request.getResourceResolver();
		Resource userResource = resolver.getResource("/content/users/"+userId);
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
			response.getWriter().write("User info is UPDATED "+userId);

		}

	}

	protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		ResourceResolver resolver = request.getResourceResolver();
		Resource userResource = resolver.getResource("/content/users/"+userId);
		resolver.delete(userResource);
		resolver.commit();		 


		response.getWriter().write("user deleted sucessfully - "+userId);
	}

}
