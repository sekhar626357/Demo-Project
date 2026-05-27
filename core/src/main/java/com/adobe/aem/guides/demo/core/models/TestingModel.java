package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TestingModel {

    @ValueMapValue
    private String textField;

    @ValueMapValue
    @Default(booleanValues = false)
    private boolean checkboxField;

    public String getTextField() {
        return textField;
    }

    public boolean isCheckboxField() {
        return checkboxField;
    }
}
