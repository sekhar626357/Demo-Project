/*package com.adobe.aem.guides.demo.core.schedulers;

import java.util.Date;
import java.util.Iterator;

import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.aem.guides.demo.core.services_impl.DemoUnitService;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;*/

//@Component(service = Runnable.class,immediate = true,property = {"scheduler.expression=*/5 * * ? * *"})
/*public class Task11 implements Runnable {
	@Reference
	DemoUnitService demoUnitService;
	@Reference
	Replicator replicator;

	@Override
	public void run() {
		try(ResourceResolver resolver = demoUnitService.getResourceResolver()){
			PageManager pageManager = resolver.adaptTo(PageManager.class);
			Page page = pageManager.getPage("/content/Demo/us/en/page1/page2");
			Iterator<Page> childPages = page.listChildren();
			while (childPages.hasNext()) {
				Page page2 = (Page) childPages.next();
					Resource contentResource = page2.getContentResource();
					ValueMap prop = contentResource.getValueMap();
					 Date expirydate  = prop.get("expirydate", Date.class);
					 Date today = new Date();
					 if(expirydate!=null && expirydate.before(today)) {
						 Session session = resolver.adaptTo(Session.class);
						 replicator.replicate(session, ReplicationActionType.DEACTIVATE, page2.getPath());
					 }else if(expirydate!=null && expirydate.after(today)) {
						 Session session = resolver.adaptTo(Session.class);
						 replicator.replicate(session, ReplicationActionType.ACTIVATE, page2.getPath());
					 }
					 
			}
		} catch (ReplicationException e) {
			
			e.printStackTrace();
		}

	}

}*/
