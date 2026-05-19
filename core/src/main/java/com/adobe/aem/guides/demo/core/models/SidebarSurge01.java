package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SidebarSurge01 {

    @ValueMapValue
    private String pFieldt5;

    @ValueMapValue
    private String pFieldt6;

    public String getPFieldt5() {
        return pFieldt5;
    }

    public String getPFieldt6() {
        return pFieldt6;
    }

}
