/*Finally, develop a path-based servlet (e.g., /bin/showConfigMessage) that, when accessed, retrieves the custom.
message from the configuration via the service and prints it in the HTTP response*/
package com.adobe.aem.guides.demo.core.servlets;

import com.adobe.aem.gguides.demo.core.services.CustomMessageService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class,enabled = true,immediate = true)
@SlingServletPaths(value = "/bin/showConfigMessage" )

public class ShowConfigMessageServlet extends SlingSafeMethodsServlet { 

    @Reference
    private CustomMessageService customMessageService;
    /*NOTE=For interface we can not create objects but 
     * by using interface reference variable we can hold implementation class object*/
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        String message = customMessageService.getCustomMessage();
        response.setContentType("text/plain");
        response.getWriter().write("Configured message: " + message);
        
        
    }
}
