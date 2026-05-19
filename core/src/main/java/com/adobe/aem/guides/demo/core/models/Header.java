package com.adobe.aem.guides.demo.core.models;

import java.util.*;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables ={Resource.class,SlingHttpServletRequest.class},
        resourceType = "Demo/components/content/header",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
        )
public class Header{

    @ValueMapValue
    private String pField;

    @ValueMapValue
    private String tField;

    @ChildResource
    private List<MfieldModel>mField;

    @ValueMapValue
    private String cBox;

    public String getPathField() {
        return pField;
    }

    public String getTextField() {
        return tField;
    }

    public List<MfieldModel> getMultiField() {
        return new ArrayList<MfieldModel>(mField);
    }

    public String getCheckBox() {
        return cBox;
    }
    public String Dinesh() {
    
    	return null;
    }


}