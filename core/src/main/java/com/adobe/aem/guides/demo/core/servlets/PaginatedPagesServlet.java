package com.adobe.aem.guides.demo.core.servlets;

import com.adobe.aem.guides.demo.core.models.PaginatedPagesModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/demo/paginatedpages")
public class PaginatedPagesServlet extends SlingAllMethodsServlet {

    @Reference 
    private transient ModelFactory modelFactory;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        PaginatedPagesModel model = modelFactory.createModel(request, PaginatedPagesModel.class);

        JsonArray currentArray = new JsonArray();
        for (String path : model.getCurrentPagePaths()) {
            currentArray.add(path);
        }

        JsonArray remainingArray = new JsonArray();
        for (String path : model.getRemainingPagePaths()) {
            remainingArray.add(path);
        }

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.add("visiblePages", currentArray);
        jsonResponse.add("hiddenPages", remainingArray);

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }
}
