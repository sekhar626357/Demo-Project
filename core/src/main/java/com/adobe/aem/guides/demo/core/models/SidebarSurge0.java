package com.adobe.aem.guides.demo.core.models;

import java.util.*;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SidebarSurge0 {

    @ValueMapValue
    private String pFieldt1;

    @ValueMapValue
    private String pFieldt2;

    @ValueMapValue
    private String pFieldt3;

    @ValueMapValue
    private String pFieldt4;

    @ChildResource
    private List<SidebarSurge01> mFieldt2;

    @ChildResource
    private List<SidebarSurge02> mFieldt3;

    @ValueMapValue
    private String pFieldt10;

    public String getPFieldt1() {
        return pFieldt1;
    }

    public String getPFieldt2() {
        return pFieldt2;
    }

    public String getPFieldt3() {
        return pFieldt3;
    }

    public String getPFieldt4() {
        return pFieldt4;
    }

    public List<SidebarSurge01> getMFieldt2() {
        return mFieldt2;
    }

    public List<SidebarSurge02> getMFieldt3() {
        return mFieldt3;
    }

    public String getPFieldt10() {
        return pFieldt10;
    }
    


}
