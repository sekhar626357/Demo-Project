/*package com.adobe.aem.guides.demo.core.listeners;

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
		EventConstants.EVENT_FILTER+"=(& (type=ACTIVATE) (paths=/content/Demo/us/en/articles/*))"}
		)
public class Task7 implements EventHandler {
	private static final Logger Log = LoggerFactory.getLogger(Task7.class);
	@Reference
	DemoUnitService demoUnitService;
	@Override
	public void handleEvent(Event event) {
		Log.info("Task7 inside handleEvent method...");
		String[] pn = event.getPropertyNames();
		for(String string : pn) {
			Log.info("Property Name-{}","Property Value-{}",string,event.getProperty(string));
		}
		String[] paths =(String[]) event.getProperty("paths");
		if(paths!=null) {
			for(String path : paths) {
				try(ResourceResolver resolver = demoUnitService.getResourceResolver()){
				Resource contentResource = resolver.getResource(path+"/jcr:content");
				ModifiableValueMap prop = contentResource.adaptTo(ModifiableValueMap.class);
				prop.put("changed1", true);
				resolver.commit();
				} catch (PersistenceException e) {
					
					e.printStackTrace();
				}
			}
		}
		

	}

}
*/