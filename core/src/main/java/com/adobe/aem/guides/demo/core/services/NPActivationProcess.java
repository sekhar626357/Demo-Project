package com.adobe.aem.guides.demo.core.services;

import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.aem.guides.demo.core.services_impl.DemoUnitService;
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
@Component(service = WorkflowProcess.class,immediate = true,
property = {"process.label = Newsportal Content Activation Process"})
public class NPActivationProcess implements WorkflowProcess{
	@Reference
	Replicator replicator;
	DemoUnitService demoUnitService;
	@Override
	public void execute(WorkItem item, WorkflowSession wfsession, MetaDataMap args) throws WorkflowException {
		try(ResourceResolver resolver = demoUnitService.getResourceResolver()){
			Session session = resolver.adaptTo(Session.class);
			String payload = item.getWorkflowData().getPayload().toString();
			replicator.replicate(session, ReplicationActionType.ACTIVATE, payload);
		} catch (ReplicationException e) {
			e.printStackTrace();
		}
	}

}
