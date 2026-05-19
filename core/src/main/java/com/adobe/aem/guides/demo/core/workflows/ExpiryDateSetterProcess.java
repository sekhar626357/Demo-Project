package com.adobe.aem.guides.demo.core.workflows;

import java.util.Calendar;
import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import com.adobe.aem.guides.demo.core.services_impl.DemoUnitService;
import com.day.cq.workflow.WorkflowSession;

@Component(
    service = WorkflowProcess.class,
    property = {
        "process.label=Set Expiry Date Based on Template"
    }
)
public class ExpiryDateSetterProcess implements WorkflowProcess {

	@Reference
	private DemoUnitService demoUnitService;

    @Override
    public void execute(WorkItem item, WorkflowSession wfSession, MetaDataMap args) {
        String path = item.getWorkflowData().getPayload().toString();

        try (ResourceResolver resolver = demoUnitService.getResourceResolver()) {

            Session session = resolver.adaptTo(Session.class);
            if (session.nodeExists(path + "/jcr:content")) {
                Node contentNode = session.getNode(path + "/jcr:content");

                // Get template property
                String templatePath = contentNode.hasProperty("cq:template")
                        ? contentNode.getProperty("cq:template").getString()
                        : "";

                // Calculate expiry date
                Calendar expiryDate = Calendar.getInstance();
                if (!templatePath.startsWith("/conf/Demo") && !templatePath.contains("/Demo")) {
                    // Set to yesterday
                    expiryDate.add(Calendar.DATE, -1);
                }

                // Set property
                contentNode.setProperty("expiryDate", expiryDate);
                session.save();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
