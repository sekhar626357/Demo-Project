package com.adobe.aem.guides.demo.core.models;
import java.util.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Author3.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Author3Impl implements Author3 {

    @ValueMapValue
    private String gName;

    @ValueMapValue
    private List<Integer> god;

    @Override
    public String getFirstName() {
        return gName;
    }

    @Override
    public List<Integer> getRollnumber() {
        return new ArrayList<Integer>(god); 
    }

}