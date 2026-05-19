package com.adobe.aem.guides.demo.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)
public class SunDAy {
    private static final Logger LOG=LoggerFactory.getLogger(SatDay.class);
    @Activate
    public void activate(){
        LOG.info("This is activate method SUNDAY");
    }
    @Deactivate
    public void deactivate(){
        LOG.info("This is deactivate method SUNDAY");
    }
    @Modified
    public void modified(){
        LOG.info("This is activate method SUNDAY");
    }
    public String print1(){
        return "Hi this is Sekhar";
    }
}
