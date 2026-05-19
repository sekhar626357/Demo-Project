package com.adobe.aem.guides.demo.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;


@Component(service = DemoConfigService.class)
@Designate(ocd = DemoConfiguration.class)
public class DemoConfigServiceImpl implements DemoConfigService {

    private String clientId;
    private String apiToken;
    private String pagePath;

    @Activate
    @Modified
    protected void activate(DemoConfiguration config) {
        this.clientId = config.clientId();
        this.apiToken = config.apiToken();
        this.pagePath = config.pagePath();
    }

    public String getClientId() {
        return clientId;
    }

    public String getApiToken() {
        return apiToken;
    }

    public String getPagePath() {
        return pagePath;
    }
}
