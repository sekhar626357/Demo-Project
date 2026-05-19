package com.adobe.aem.guides.demo.core.models;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import java.util.*;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Prog2 {

    @Inject
    private String title;

    @Inject
    private String path;

    @Inject
    @Named("list/.")
    private List<Prog3> paruchuru;


    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public List<Prog3> getParuchuruPoint() {
        return paruchuru;
    }


    
}
