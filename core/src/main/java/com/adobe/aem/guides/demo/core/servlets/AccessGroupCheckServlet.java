package com.adobe.aem.guides.demo.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import javax.jcr.Session;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.Group;

@Component(service = Servlet.class,immediate = true)
@SlingServletPaths(value = {"/bin/check-access"})
public class AccessGroupCheckServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        ResourceResolver resourceResolver = request.getResourceResolver();
        String userId = resourceResolver.getUserID();

        Session session = resourceResolver.adaptTo(Session.class);

        try {
            if (session != null) {
                UserManager userManager = ((org.apache.jackrabbit.api.JackrabbitSession) session).getUserManager();
                Authorizable user = userManager.getAuthorizable(userId);
                Authorizable accessGroupAuth = userManager.getAuthorizable("Access");

                if (user != null && accessGroupAuth != null && accessGroupAuth.isGroup()) {
                    Group accessGroup = (Group) accessGroupAuth;
                    if (accessGroup.isMember(user)) {
                        response.getWriter().write("User " + userId + " has access.");
                    } else {
                        response.getWriter().write("User " + userId + " does NOT have access.");
                    }
                } else {
                    response.getWriter().write("User or Access group not found.");
                }
            } else {
                response.getWriter().write("Session is null.");
            }
        } catch (RepositoryException e) {
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
