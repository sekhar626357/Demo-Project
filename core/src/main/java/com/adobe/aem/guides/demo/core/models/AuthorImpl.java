package com.adobe.aem.guides.demo.core.models;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
//import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
//import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;


@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Author.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class AuthorImpl implements Author{

    @SlingObject
    private Resource currentResource;
    
    @ScriptVariable
    private Page currentPage;

    @Inject
    @Via("resource")
    @Default(values = "AEM")
    String fName;

    @Inject
    @Via("resource")
    @Default(values = "GEEKS")
    String lName;

    @Inject
    @Via("resource")
    boolean proFessor;

    @Override
    public String getFirstName() {
        return fName;
    }

    @Override
    public String getLastName() {
        return lName;
    }

    @Override
    public boolean getIsProfessor() {
        return proFessor;
    }

    @Override
    public String getPageTitle() {
        return currentPage.getTitle();
    }

    @Override
    public String getResource() {
       return currentResource.getPath();
    }

    


}
