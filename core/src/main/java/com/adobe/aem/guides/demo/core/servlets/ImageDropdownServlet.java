package com.adobe.aem.guides.demo.core.servlets;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import org.apache.commons.collections.IteratorUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.*;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.framework.Constants;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.*;

@Component(
    service = Servlet.class,
    property = {
        Constants.SERVICE_DESCRIPTION + "=Image Dropdown DataSource Servlet",
        "sling.servlet.paths=/bin/image-dropdown",
        "sling.servlet.methods=GET"
    }
)
public class ImageDropdownServlet extends SlingSafeMethodsServlet {

    private static final String IMAGE_FOLDER_PATH = "/content/dam/images";

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        ResourceResolver resolver = request.getResourceResolver();
        List<Resource> dropdownOptions = new ArrayList<>();

        Resource imageFolder = resolver.getResource(IMAGE_FOLDER_PATH);
        if (imageFolder != null) {
            for (Resource image : imageFolder.getChildren()) {
                String name = image.getName();
                String path = image.getPath();

                ValueMap vm = new ValueMapDecorator(new HashMap<>());
                vm.put("text", name);
                vm.put("value", path);

                dropdownOptions.add(new ValueMapResource(resolver, new ResourceMetadata(), "nt:unstructured", vm));
            }
        }

        DataSource ds = new SimpleDataSource(dropdownOptions.iterator());
        request.setAttribute(DataSource.class.getName(), ds);
    }
}
 