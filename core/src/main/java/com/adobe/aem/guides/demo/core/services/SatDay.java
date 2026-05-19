package com.adobe.aem.guides.demo.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)
public class SatDay {
    private static final Logger LOG=LoggerFactory.getLogger(SatDay.class);
    @Reference
    SunDAy sunDAy;
    @Activate
    public void activate(){
        String jam=sunDAy.print1();
        LOG.info("This is activate method");
        LOG.info("jam{}",jam);
    }
    @Deactivate
    public void deactivate(){
        LOG.info("This is deactivate method");
    }
    @Modified
    public void modified(){
        LOG.info("This is activate method");
    }
}
