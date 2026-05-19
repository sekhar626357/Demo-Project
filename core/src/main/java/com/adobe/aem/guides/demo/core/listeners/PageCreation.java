package com.adobe.aem.guides.demo.core.listeners; 
import javax.jcr.Session; 
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
import com.day.cq.workflow.WorkflowException; 
import com.day.cq.workflow.WorkflowService; 
import com.day.cq.workflow.WorkflowSession; 
import com.day.cq.workflow.exec.WorkflowData; 
import com.day.cq.workflow.model.WorkflowModel; 
@Component(service = EventHandler.class, immediate = true, 
property = { 
		EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED" 
} 
		) 
public class PageCreation implements EventHandler { 
	private static final Logger log = LoggerFactory.getLogger(PageCreation.class); 

	@Reference 
	private WorkflowService workflowService; 

	@Reference 
	private DemoUnitService demoUnitService; 



	@Override 
	public void handleEvent(Event event) { 
		log.info("Page creation event triggered"); 
		handleResourceAddedEvent(event); 
	} 

	private void handleResourceAddedEvent(Event event) { 
		String path = (String) event.getProperty("path"); 

		//        log.info("Resource added at path: {}", path); 

		 
		
			try(ResourceResolver	resourceResolver = demoUnitService.getResourceResolver()){
			Resource resource = resourceResolver.getResource(path); 

			if (resource != null && resource.isResourceType("cq:Page")) { 
				log.info("Page created at path: {}", path); 
				startWorkflow(path); 
			} 
		} 
	} 


	private void startWorkflow(String payloadPath) { 
		try (ResourceResolver	resourceResolver = demoUnitService.getResourceResolver()) { 
			Session session = resourceResolver.adaptTo(Session.class); 
			WorkflowSession workflowSession = workflowService.getWorkflowSession(session); 
			String workflowModelPath = "/var/workflow/models/demo-expiry-date"; // Replace with your workflow model path 
			WorkflowModel workflowModel = workflowSession.getModel(workflowModelPath); 
			WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", payloadPath); 
			workflowSession.startWorkflow(workflowModel, workflowData); 
			log.info("Workflow started for payload: {}", payloadPath); 
		} catch (WorkflowException e) { 
			log.error("WorkflowException while starting workflow", e); 
		} catch (Exception e) { 
			log.error("Exception while starting workflow", e); 
		} 
	} 
} 