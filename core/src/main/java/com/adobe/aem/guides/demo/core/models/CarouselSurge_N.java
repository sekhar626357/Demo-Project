package com.adobe.aem.guides.demo.core.models;
import java.util.*;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CarouselSurge_N {

    @ValueMapValue
    private String mText;

    @ValueMapValue
    private String pField;

    @ChildResource
    private List<CarouselSurge_N1> nField;

    public String getTextField() {
        return mText;
    }

    public String getPathField() {
        return pField;
    }

    public List<CarouselSurge_N1> getNestField() {
        return nField;
    }

}
