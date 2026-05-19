package com.adobe.aem.guides.demo.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class UserListModel {

    @Self
    private Resource currentResource;

    private List<Map<String, String>> users;

    @PostConstruct
    protected void init() {
        users = new ArrayList<>();
        ResourceResolver resolver = currentResource.getResourceResolver();
        Resource userRoot = resolver.getResource("/content/users");

        if (userRoot != null) {
            for (Resource user : userRoot.getChildren()) {
                ValueMap props = user.getValueMap();
                Map<String, String> userData = new HashMap<>();
                userData.put("firstName", props.get("firstName", ""));
                userData.put("lastName", props.get("lastName", ""));
                userData.put("email", props.get("email", ""));
                userData.put("phone", props.get("phone", ""));
                users.add(userData);
            }
        }
    }

    public List<Map<String, String>> getUsers() {
        return users;
    }
}
