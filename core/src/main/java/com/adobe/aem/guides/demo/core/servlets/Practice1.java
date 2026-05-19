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
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

@Component(service = Servlet.class, immediate = true)
@SlingServletResourceTypes(resourceTypes = { "Demo/dinesh/Practice1"})
public class Practice1 extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(Practice1.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        ResourceResolver resolver = request.getResourceResolver();

        PageManager pageManager = resolver.adaptTo(PageManager.class);
        if (pageManager == null) {
            log.error("PageManager could not be adapted from ResourceResolver.");
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"PageManager adaptation failed.\"}");
            return;
        }

        Page articlePage = pageManager.getPage("/content/Demo/us/en/articles");
        if (articlePage == null) {
            log.error("Article root page not found at /content/Demo/us/en/articles.");
            response.setStatus(SlingHttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\": \"Articles root page not found.\"}");
            return;
        }

        // Build JSON Array of existing child pages
        JsonArrayBuilder pageListJson = Json.createArrayBuilder();
        Iterator<Page> childPages = articlePage.listChildren();

        while (childPages.hasNext()) {
            Page page = childPages.next();
            JsonObjectBuilder pageJson = Json.createObjectBuilder()
                    .add("title", page.getTitle() != null ? page.getTitle() : "Untitled")
                    .add("path", page.getPath());
            pageListJson.add(pageJson);
        }

        // Create multiple pages under /articles
        String parentPath = "/content/Demo/us/en/articles";
        String templatePath = "/conf/Demo/settings/wcm/templates/sai-temp";

        for (int i = 26; i <= 30; i++) {  // Example: article-21 to article-25
            String pageName = "article-" + i;
            String pageTitle = "Article-" + i;
            
            Page existingPage = pageManager.getPage(parentPath + "/" + pageName);
            if (existingPage == null) {
                try {
                    pageManager.create(parentPath, pageName, templatePath, pageTitle);
                    log.info("Page created successfully: {}", pageName);
                } catch (WCMException e) {
                    log.error("Error while creating page: {}", pageName, e);
                }
            } else {
                log.info("Page already exists: {}", pageName);
            }
        }

        try {
            resolver.commit();
            log.info("All changes committed to the JCR.");
        } catch (Exception e) {
            log.error("Error while committing changes to the repository.", e);
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Failed to commit changes.\"}");
            return;
        }

        // Write the child page list JSON to the response
        response.getWriter().write(pageListJson.build().toString());
    }
}
