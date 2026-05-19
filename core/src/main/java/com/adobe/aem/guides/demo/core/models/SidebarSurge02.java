package com.adobe.aem.guides.demo.core.models;
import java.util.*;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SidebarSurge02 {
    
    @ValueMapValue
    private String pFieldt7;

    @ValueMapValue
    private String pFieldt8;

    @ChildResource
    private List<SidebarSurge02_1>nFieldt3;

    public String getPFieldt7() {
        return pFieldt7;
    }

    public String getPFieldt8() {
        return pFieldt8;
    }

    public List<SidebarSurge02_1> getNFieldt3() {
        return nFieldt3;
    }

}
