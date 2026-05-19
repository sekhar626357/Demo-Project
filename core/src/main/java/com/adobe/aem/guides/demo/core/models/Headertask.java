package com.adobe.aem.guides.demo.core.models;
import java.util.*;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Headertask {

        @ValueMapValue
        private String pField;

        @ValueMapValue
        private String tField;

        @ChildResource
        private List<HeaderForMultifield> mField;

        @ValueMapValue
        private String cBox;

        public String getPathField() {
            return pField;
        }

        public String getTextField() {
            return tField;
        }

        public List<HeaderForMultifield> getMultiField() {
            return mField;
        }

        public String getCheckBox() {
            return cBox;
        }
        
}
