package com.adobe.aem.guides.demo.core.listeners;

import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.demo.core.services_impl.DemoUnitService;
import com.day.cq.replication.ReplicationAction;

@Component(service=EventHandler.class, immediate=true,
            property = {
                
                EventConstants.EVENT_TOPIC+"="+ReplicationAction.EVENT_TOPIC,
                EventConstants.EVENT_FILTER+"=(& (type=ACTIVATE) (paths=/content/Demo/us/*))"
            })
public class DemoEvent implements EventHandler{

    private static final Logger LOG=LoggerFactory.getLogger(DemoEvent.class);

    @Reference
    DemoUnitService demoUnitService;
    @Override
    public void handleEvent(Event event) {

        LOG.info("inside handle event method....");

        String [] paths =(String[]) event.getProperty("paths");
        for(String path:paths){
            ResourceResolver resolver=demoUnitService.getResourceResolver();
            Resource contentResource = resolver.getResource(path+"/jcr:content");
            ModifiableValueMap mProp=contentResource.adaptTo(ModifiableValueMap.class);
            mProp.put("pageActivated",true);
            try {
                resolver.commit();
                resolver.close();
            } catch (PersistenceException e) {
                
                e.printStackTrace();
            }
        }
        }

        
    }


