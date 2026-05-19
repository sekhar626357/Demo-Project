package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException; //Handles input/output exceptions.
import javax.servlet.Servlet;//General servlet-related classes.
import javax.servlet.ServletException;//General servlet-related classes.

import org.apache.commons.lang.RandomStringUtils;// Utility for generating random strings.
import org.apache.sling.api.SlingHttpServletRequest;//Specific to Sling, used for handling HTTP requests and responses.
import org.apache.sling.api.SlingHttpServletResponse;//Specific to Sling, used for handling HTTP requests and responses.
import org.apache.sling.api.servlets.SlingAllMethodsServlet;//Specific to Sling, used for handling HTTP requests and responses.
import org.osgi.service.component.annotations.Component;// Marks the class as an OSGi component.

@Component(service = Servlet.class,immediate = true,
property={"sling.servlet.paths=/bin/tas"})
public class ServTask_1 extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res)
            throws ServletException, IOException {

              String sessionpar = req.getParameter("session");
              String numberpar = req.getParameter("random");
              //Retrieves the query parameters session and random from the request.

             res.setContentType("application/json");//Specifies the response content type as JSON.
            /*Uses RandomStringUtils to generate:
            A 6-character alphanumeric string (alphword).*/
            
             String alphword = RandomStringUtils.randomAlphanumeric(6);
             //If numberpar equals "alphanum", writes the uppercase version of alphword to the response.
                if ("session".equals(sessionpar)&&"alphanum".equals(numberpar)){

                    res.getWriter().write(alphword.toUpperCase());

                }

                
    }
}