// Then, implement a service that reads this configuration value.
package com.adobe.aem.guides.demo.core.services.impl;

import com.adobe.aem.guides.demo.core.config.CustomMessageConfig;
import com.adobe.aem.gguides.demo.core.services.CustomMessageService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = CustomMessageService.class)
@Designate(ocd = CustomMessageConfig.class)
public class CustomMessageServiceImpl implements CustomMessageService {

    private String customMessage;
/* 
 * @Activate
 * @Modified
 * @Deactivate
 * These three annotations are related to lifeCycle events*/
    
    @Activate
    @Modified
    protected void activate(CustomMessageConfig config) {
        this.customMessage = config.customMessage();
    }

    @Override
    public String getCustomMessage() {
        return customMessage;
    }
}
