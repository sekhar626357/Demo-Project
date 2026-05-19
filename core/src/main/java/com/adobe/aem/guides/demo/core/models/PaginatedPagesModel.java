package com.adobe.aem.guides.demo.core.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(
    adaptables = SlingHttpServletRequest.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
    resourceType = "/apps/Demo/components/task/task-17"
)
@Exporter(
    name = "jackson",
    extensions = "json",
    selector = "model",
    options = {
        @ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value = "false")
    }
)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PaginatedPagesModel {

    @Inject
    private SlingHttpServletRequest request;

    @Inject
    private String parentPath;

    private List<String> currentPagePaths = new ArrayList<>();
    private List<String> remainingPagePaths = new ArrayList<>();

    @PostConstruct
    protected void init() {
        int pageSize = getIntParam("pageSize", 1);
        int pageLimit = getIntParam("pageLimit", 3);

        if (parentPath == null || parentPath.isEmpty()) return;

        Resource parentResource = request.getResourceResolver().getResource("/content/Demo/us/en");

        if (parentResource != null) {
            List<Resource> children = new ArrayList<>();
            Iterator<Resource> iterator = parentResource.listChildren();
            iterator.forEachRemaining(children::add);

            int startIndex = (pageSize - 1) * pageLimit;
            int endIndex = Math.min(startIndex + pageLimit, children.size());

            for (int i = 0; i < children.size(); i++) {
                String childPath = children.get(i).getPath();
                if (i >= startIndex && i < endIndex) {
                    currentPagePaths.add(childPath);
                } else {
                    remainingPagePaths.add(childPath);
                }
            }
        }
    }

    private int getIntParam(String name, int defaultValue) {
        try {
            String val = request.getParameter(name);
            return val != null ? Integer.parseInt(val) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public List<String> getCurrentPagePaths() {
        return currentPagePaths;
    }

    public List<String> getRemainingPagePaths() {
        return remainingPagePaths;
    }
}
