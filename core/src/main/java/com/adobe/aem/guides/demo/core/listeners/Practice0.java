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

@Component(service = EventHandler.class,immediate = true,
			property = {EventConstants.EVENT_TOPIC+"="+ReplicationAction.EVENT_TOPIC,
						EventConstants.EVENT_FILTER+"=(& (type=ACTIVATE) (paths=/content/Demo/us/en/articles/*))"} )
public class Practice0 implements EventHandler{
	@Reference
	DemoUnitService demoUnitService;
private static final Logger Log = LoggerFactory.getLogger(Practice0.class); 
	@Override
	public void handleEvent(Event event) {
		
		Log.info("handleEvent method is executed...");
		
		String[] paths = event.getPropertyNames();
		for (String path : paths) {
			Log.info("Property Name-{}","Property Value-{}",path,event.getProperty(path));
		}
		String[] path = (String[])event.getProperty("paths");
		for(String string:path) {
			try(ResourceResolver resolver = demoUnitService.getResourceResolver();){
				Resource contentResource = resolver.getResource(string + "/jcr:content");
				ModifiableValueMap mProp = contentResource.adaptTo(ModifiableValueMap.class);
				mProp.put("pageActivate", true);
				resolver.commit();
				
				resolver.close();
			} catch (PersistenceException e) {
				
				e.printStackTrace();
			}
			
		}
	}

}
