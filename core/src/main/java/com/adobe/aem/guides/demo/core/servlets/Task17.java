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

@Component(service = Servlet.class)
@SlingServletPaths("/bin/newsportal.model.json")
public class Task17 extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int pageLimit = getIntParameter(request, "pageLimit", 10); // how many results per page
        int pageSize = getIntParameter(request, "pageSize", 0);    // which page (0-based index)

        int startIndex = pageLimit * pageSize;
        int endIndex = startIndex + pageLimit;

        JsonArrayBuilder pageListJson = Json.createArrayBuilder();
        ResourceResolver resolver = request.getResourceResolver();
        PageManager pageManager = resolver.adaptTo(PageManager.class);

        if (pageManager != null) {
            Page rootPage = pageManager.getPage("/content/Demo/us/en");

            if (rootPage != null) {
                Iterator<Page> childPages = rootPage.listChildren();
                int count = 0;

                while (childPages.hasNext()) {
                    Page child = childPages.next();

                    if (count >= startIndex && count < endIndex) {
                        JsonObjectBuilder pageJson = Json.createObjectBuilder();
                        pageJson.add("path", child.getPath());
                        pageListJson.add(pageJson);
                    }

                    count++;
                }
            }
        }

        response.getWriter().write(pageListJson.build().toString());
    }

    private int getIntParameter(SlingHttpServletRequest request, String name, int defaultValue) {
        try {
            String param = request.getParameter(name);
            return param != null ? Integer.parseInt(param) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
