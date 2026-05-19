package com.adobe.aem.guides.demo.core.models;
import java.util.*;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Sidebar {

    @ValueMapValue
    private String tab1Logo;

    @ValueMapValue
    private String tab1Mobile;

    @ValueMapValue
    private String tab1Link;

    @ValueMapValue
    private String tab1Box;

    @ChildResource
    private List<Siderbar2> mField;

    @ChildResource
    private List<Sidebar3> mField1;

    @ValueMapValue
    private String cList;

    public String getTab1Logo() {
        return tab1Logo;
    }

    public String getTab1Mobile() {
        return tab1Mobile;
    }

    public String getTab1Link() {
        return tab1Link;
    }

    public String getTab1Box() {
        return tab1Box;
    }

    public List<Siderbar2> getMultiField(){
        return mField;
    }

    public List<Sidebar3> getMultiField1(){
        return mField1;
    }

    public String getCountryList() {
        return cList;
    }
    


}
