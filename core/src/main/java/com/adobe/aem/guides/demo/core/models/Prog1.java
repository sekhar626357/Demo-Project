package com.adobe.aem.guides.demo.core.models;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import java.util.*;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Prog1 {

    @Inject
    @Named("accordions/.")
    private List<Prog2> dinesh;

    public List<Prog2> getSaiDinesh() {
        return dinesh;
    }
    

}
