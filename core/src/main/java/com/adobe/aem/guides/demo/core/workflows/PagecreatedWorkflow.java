package com.adobe.aem.guides.demo.core.workflows; 
import java.util.Calendar; 
import javax.jcr.Node; 
import javax.jcr.Session; 
import org.apache.sling.api.resource.ResourceResolver; 
import org.osgi.service.component.annotations.Component; 
import org.osgi.service.component.annotations.Reference; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.demo.core.services_impl.DemoUnitService;
import com.adobe.granite.workflow.WorkflowSession; 
import com.adobe.granite.workflow.exec.WorkItem; 
import com.adobe.granite.workflow.exec.WorkflowProcess; 
import com.adobe.granite.workflow.metadata.MetaDataMap; 
 
@Component(service = WorkflowProcess.class, property = {"process.label=Page Created"}) 
public class PagecreatedWorkflow implements WorkflowProcess { 
    private static final Logger log = LoggerFactory.getLogger(PagecreatedWorkflow.class); 
    @Reference 
	private DemoUnitService demoUnitService; 
    @Override 
    public void execute(WorkItem workItem, WorkflowSession workflowSession, 
MetaDataMap metaDataMap) { 
        String payloadPath = workItem.getWorkflowData().getPayload().toString(); 
        
        
           
        	try(ResourceResolver resourceResolver = demoUnitService.getResourceResolver()){ 
 
            Session session = resourceResolver.adaptTo(Session.class); 
            Node pageNode = session.getNode(payloadPath); 
 
            if (pageNode != null && pageNode.hasNode("jcr:content")) { 
                Node contentNode = pageNode.getNode("jcr:content"); 
                Calendar currentDate = Calendar.getInstance(); 
                contentNode.setProperty("expired", currentDate); 
                session.save(); 
                log.info("Property 'expired' added to jcr:content of page at {}", payloadPath); 
            } else { 
                log.warn("jcr:content node not found for page at {}", payloadPath); 
            } 
        } catch (Exception e) { 
            log.error("Error adding property to jcr:content of page at {}", payloadPath, e); 
            throw new RuntimeException(e); 
        } 
        }}
