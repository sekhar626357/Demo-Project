package com.adobe.aem.guides.demo.core.models;
import java.util.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
//import org.slf4j.LoggerFactory;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Author2.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Author2Impl implements Author2{
    

    @ValueMapValue
    @Default(values = "Dinesh")
    private String fName;

    @ValueMapValue
    private List<String> bName;

    @Override
    public String getAuthorName() {
        return fName;
    }

    @Override
    public List<String> getAuthorBooks() {
        if(bName!=null){
            return new ArrayList<String>(bName);
        }else{
            return Collections.emptyList();
        }
    }

}
