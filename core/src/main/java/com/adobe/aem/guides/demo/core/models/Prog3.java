package com.adobe.aem.guides.demo.core.models;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class Prog3 {

    @Inject
    private String description;
 
    public String getDescription() {
        return description;
    }

    
}
