package com.adobe.aem.guides.demo.core.listeners;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventHandler;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;

import com.adobe.aem.guides.demo.core.services_impl.DemoUnitService;
import com.day.cq.workflow.WorkflowService;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.model.WorkflowModel;

@Component(
		immediate = true,
		service = EventHandler.class,
				 property = {
					        EventConstants.EVENT_TOPIC + "=" + "org/apache/sling/api/resource/Resource/ADDED",
					        EventConstants.EVENT_FILTER + "=" + "(path=/content/Demo/.*)"
					    }
		)
public class PageCreationEventHandler implements EventHandler {

	@Reference
	private WorkflowService workflowService;

	@Reference
	private DemoUnitService demoUnitService;

	@Override
    public void handleEvent(Event event) {
        String path = (String) event.getProperty("path");

        if (path != null && path.startsWith("/content/demo/") && !path.contains("/jcr:content")) {
            try (ResourceResolver resolver = demoUnitService.getResourceResolver()) {
                Session session = resolver.adaptTo(Session.class);
                if (session.nodeExists(path)) {
                    Node node = session.getNode(path);
                    if ("cq:Page".equals(node.getPrimaryNodeType().getName())) {
                        WorkflowSession wfSession = workflowService.getWorkflowSession(session);
                        WorkflowModel model = wfSession.getModel("/var/workflow/models/demo-expiry-date");
                        WorkflowData data = wfSession.newWorkflowData("JCR_PATH", path);
                        wfSession.startWorkflow(model, data);
                    }
                }
            } catch (RepositoryException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	}



