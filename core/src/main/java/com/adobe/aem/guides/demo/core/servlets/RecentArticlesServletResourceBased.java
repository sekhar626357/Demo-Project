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

@Component(service = Servlet.class)
@SlingServletResourceTypes(
    resourceTypes = "demo/components/userdata",
    methods = {"GET", "POST", "PUT", "DELETE"},
    		extensions= {"json","txt","html"},
    				selectors= {"s4","Sports"}
)
public class RecentArticlesServletResourceBased extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        ResourceResolver resolver = request.getResourceResolver();
        Resource userResource = resolver.getResource("/content/users");
        if (userResource != null) {
            Iterator<Resource> childUsersList = userResource.listChildren();
            JsonArrayBuilder userJsonList = Json.createArrayBuilder();
            while (childUsersList.hasNext()) {
                Resource childUserResource = childUsersList.next();
                ValueMap properties = childUserResource.getValueMap();

                JsonObjectBuilder userJson = Json.createObjectBuilder();
                userJson.add("firstName", properties.get("firstName", ""));
                userJson.add("lastName", properties.get("lastName", ""));
                userJson.add("email", properties.get("email", ""));
                userJson.add("phone", properties.get("phone", ""));
                userJsonList.add(userJson);
            }
            response.setContentType("application/json");
            response.getWriter().write(userJsonList.build().toString());
    		response.getWriter().write("Responce From ResourceBased-Servlet-GET");

        }
    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        ResourceResolver resolver = request.getResourceResolver();
        Resource userResource = resolver.getResource("/content/users");

        String userId = request.getParameter("userId");
        Map<String, Object> properties = new HashMap<>();
        properties.put("firstName", request.getParameter("firstName"));
        properties.put("lastName", request.getParameter("lastName"));
        properties.put("email", request.getParameter("email"));
        properties.put("phone", request.getParameter("phone"));

        resolver.create(userResource, userId, properties);
        resolver.commit();

        response.getWriter().write("User successfully created: " + userId);
    }

    @Override
    protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        ResourceResolver resolver = request.getResourceResolver();
        Resource userResource = resolver.getResource("/content/users/" + userId);

        if (userResource != null) {
            ModifiableValueMap mProp = userResource.adaptTo(ModifiableValueMap.class);

            if (request.getParameter("firstName") != null)
                mProp.put("firstName", request.getParameter("firstName"));
            if (request.getParameter("lastName") != null)
                mProp.put("lastName", request.getParameter("lastName"));
            if (request.getParameter("email") != null)
                mProp.put("email", request.getParameter("email"));
            if (request.getParameter("phone") != null)
                mProp.put("phone", request.getParameter("phone"));

            resolver.commit();
            response.getWriter().write("User info updated: " + userId);
        }
    }

    @Override
    protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        ResourceResolver resolver = request.getResourceResolver();
        Resource userResource = resolver.getResource("/content/users/" + userId);

        if (userResource != null) {
            resolver.delete(userResource);
            resolver.commit();
            response.getWriter().write("User deleted successfully: " + userId);
        }
    }
}
