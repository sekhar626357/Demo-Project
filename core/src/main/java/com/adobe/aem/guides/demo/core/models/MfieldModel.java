package com.adobe.aem.guides.demo.core.models;

import java.util.Date;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class MfieldModel {

    @ValueMapValue
    private String tField1;

    @ValueMapValue
    private Date dPicker;

    public String getTextField1() {
        return tField1;
    }

    public Date getDatePicker() {
        return dPicker;
    }
 
}