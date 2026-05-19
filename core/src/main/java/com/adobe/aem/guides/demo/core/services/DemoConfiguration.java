package com.adobe.aem.guides.demo.core.services;

import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Demo Configuration")
public @interface DemoConfiguration {
    String clientId();
    String apiToken();
    String pagePath();
}