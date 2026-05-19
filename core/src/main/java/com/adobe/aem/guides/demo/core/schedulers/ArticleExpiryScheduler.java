package com.adobe.aem.guides.demo.core.schedulers;
import java.util.Date;
import java.util.Iterator;
import javax.jcr.Session;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.aem.guides.demo.core.services_impl.DemoUnitService;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = Runnable.class,immediate = true,property = {"scheduler.expression=0 */3 * ? * *"})
//@Designate(ocd=ArticleExpiryConfiguration.class)
public class ArticleExpiryScheduler implements Runnable{
	private static final Logger Log = LoggerFactory.getLogger(ArticleExpiryScheduler.class);
	@Reference
	DemoUnitService demoUnitService;
	
	@Reference
	Replicator replicator;//to activate and deactivate pages programmatically.
	
	/*@Reference
	Scheduler scheduler;
	
	@Activate
	public void activate(ArticleExpiryConfiguration config) {
		schedule(config);

	}
	@Modified
	public void update(ArticleExpiryConfiguration config) {
		schedule(config);
	}
	
	public void schedule(ArticleExpiryConfiguration config) {
		
		 if(config.enable()) {
			 ScheduleOptions options = scheduler.EXPR(config.schedulerExpression());
			 options.name(config.schedulerName());
			 options.canRunConcurrently(false);
			scheduler.schedule(this, options);
		 }else {
			 scheduler.unschedule(config.schedulerName());
		 }
		
	}*/
	
	@Override
	public void run() {
		/*Log.info("inside run Method...");
		//-->/content/Demo/us/en/articles
		try(ResourceResolver resolver = demoUnitService.getResourceResolver()){
			PageManager pageManager = resolver.adaptTo(PageManager.class);
			Page articlePage = pageManager.getPage("/content/Demo/us/en/articles");//without page path we can't create page object.

			Iterator<Page> childPages = articlePage.listChildren();
			while (childPages.hasNext()) {
				Page page = (Page) childPages.next();
				Resource contentResource = page.getContentResource();
				ValueMap properties = contentResource.getValueMap();
				Date articleExpiry = properties.get("articleExpiry", Date.class);
				Date today = new Date();
				if(articleExpiry!=null && articleExpiry.compareTo(today)<0) {
					Session session = resolver.adaptTo(Session.class);
					replicator.replicate(session, ReplicationActionType.ACTIVATE, page.getPath());
					
				}
			}
		} catch (ReplicationException e) {
			e.printStackTrace();
		}*/
		
	}

}

