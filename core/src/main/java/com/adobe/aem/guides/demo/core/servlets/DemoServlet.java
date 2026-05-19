package com.adobe.aem.guides.demo.core.servlets;


import com.adobe.aem.guides.demo.core.services.DemoConfigService;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.IOException;

@Component(
    service = Servlet.class,
    property = {
        "sling.servlet.paths=/bin/demo/update-jcr-content"
    }
)
public class DemoServlet extends SlingAllMethodsServlet { 

    @Reference
    private DemoConfigService configService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        ResourceResolver resolver = request.getResourceResolver();
        String pagePath = configService.getPagePath();

        Resource resource = resolver.getResource(pagePath + "/jcr:content");

        if (resource != null) {
            ModifiableValueMap properties = resource.adaptTo(ModifiableValueMap.class);
            if (properties != null) {
                properties.put("clientId", configService.getClientId());
                properties.put("apiToken", configService.getApiToken());

                Session session = resolver.adaptTo(Session.class);
                if (session != null) {
                    try {
						session.save();
					} catch (RepositoryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    response.getWriter().write("Properties updated successfully.");
                }
            }
        } else {
            response.getWriter().write("Invalid page path: " + pagePath);
        }
    }
}

