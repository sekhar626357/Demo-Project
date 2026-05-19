/*package com.adobe.aem.guides.demo.core.schedulers;

import java.util.Iterator;

import javax.jcr.Session;

import java.util.Date;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.demo.core.services_impl.DemoUnitService;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;



@Component(service = Runnable.class,immediate = true)
public class DemoScheduler implements Runnable {

    private static final Logger LOG= LoggerFactory.getLogger(DemoScheduler.class);

    @Reference
    DemoUnitService demoUnitService;

    @Reference
    Replicator replicator;

    @Reference
    Scheduler scheduler; 

    @Override
    public void run() {
        LOG.info("Inside run method ..");
        try(ResourceResolver resolver =demoUnitService.getResourceResolver();){
            PageManager pageManager=resolver.adaptTo(PageManager.class);
            Page demoPage=pageManager.getPage("/content/Demo/us/en");
            Iterator<Page> childPages = demoPage.listChildren();
            while (childPages.hasNext()) 
            {
            Page page = (Page) childPages.next();
            Resource ContentResource= page.getContentResource();
            ValueMap properties = ContentResource.getValueMap();
            Date articleExpiry=properties.get("articleExpiry",Date.class);
            Date toDay= new Date();
            if(articleExpiry!=null && articleExpiry.compareTo(toDay)<0){
                Session session=resolver.adaptTo(Session.class);
                replicator.replicate(session, ReplicationActionType.DEACTIVATE, page.getPath());
            }
        } 
    } catch (ReplicationException e) {
       
        e.printStackTrace();
    }
        
}   
}

*/
    

