package com.adobe.aem.guides.demo.core.models;
 
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
 
import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
 
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AccordionListModel {
 
    @Inject
    private String title;
 
    @Inject
    @Named("accordions/.")
    public List<AccordionModel> accordionList;
 
    public List<AccordionModel> getAccordionList() {
        return accordionList;
    }
 
    public String getTitle() {
        return title;
    }
 
    public boolean isConfigured() {
        return accordionList != null && !accordionList.isEmpty();
    }
 
}
