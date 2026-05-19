package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.*;
@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Sidebar3 {

    @ValueMapValue
    private String dIcon;

    @ValueMapValue
    private String mIcon;

    @ChildResource
    private List<Sidebar4> mField2;

    public String getDeskIcon() {
        return dIcon;
    }

    public String getMobileIcon() {
        return mIcon;
    }

    public List<Sidebar4> getMultifieldField2() {
        return mField2;
    }
    
}
