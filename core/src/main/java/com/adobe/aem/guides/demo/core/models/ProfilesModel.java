package com.adobe.aem.guides.demo.core.models;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class,SlingHttpServletRequest.class},
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
resourceType = {"/apps/Demo/components/task/profiles"})

public class ProfilesModel {

    @Inject
    @Default(values = "")
    private String name;

    @Inject
    @Default(values = "")
    private String selectedImage;

    public String getName() {
        return name;
    }

    public String getSelectedImage() {
        return selectedImage;
    }

    public String getImagePath() {
        if (selectedImage != null && !selectedImage.isEmpty()) {
            return "/content/dam/images/" + selectedImage;
        }
        return "";
    }
}
