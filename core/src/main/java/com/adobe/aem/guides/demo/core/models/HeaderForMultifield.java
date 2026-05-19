package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class},
                    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderForMultifield {

    @ValueMapValue
    private String mText;

    @ValueMapValue
    private String mPicker;

    public String getMultiText() {
        return mText;
    }

    public String getMultiPicker() {
        return mPicker;
    }

    

}
