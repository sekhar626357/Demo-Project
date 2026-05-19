package com.adobe.aem.guides.demo.core.models;
import org.apache.sling.api.SlingHttpServletRequest;
//import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Author1.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AuthoImpl1 implements Author1 {

    @ScriptVariable
    private Page currentPage;

    @ValueMapValue
    @Default(values = "Dinesh Kumar.P😍")
    private  String fName;

    @ValueMapValue
    @Default(longValues = 916303025113L)
    private long mNum;

    @ValueMapValue
    @Default(booleanValues = true)
    private boolean ifCompleted;

    @Override
    public String getFullName() {
        return fName;
    }

    @Override
    public long getMobileNumber() {
        return mNum;
    }

    @Override
    public boolean getIsCompleted() {
       return ifCompleted;
    }

    @Override
    public String getPageTitle() {
        return currentPage.getTitle();
    }
}
