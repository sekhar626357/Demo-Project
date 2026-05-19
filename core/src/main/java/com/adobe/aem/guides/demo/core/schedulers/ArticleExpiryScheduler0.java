/*package com.adobe.aem.guides.demo.core.schedulers;

import java.util.Iterator;

import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
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

@Component(service = Runnable.class,immediate = true)
@Designate(ocd=ArticleExpiryScheduler0Configuration.class)
public class ArticleExpiryScheduler0 implements Runnable{
	private static final Logger Log = LoggerFactory.getLogger(ArticleExpiryScheduler0.class);
	@Reference
	DemoUnitService demoUnitService;
	@Reference
	Replicator replicator;
	@Reference
	Scheduler scheduler;
	@Activate
	public void activate(ArticleExpiryScheduler0Configuration config) {
		if(config.enable()) {
			ScheduleOptions options = scheduler.EXPR(config.cronExpression());
			options.canRunConcurrently(false);
			options.name(config.schedulerName());
			scheduler.schedule(this, options);
		}else {
			scheduler.unschedule(config.schedulerName());
		}
	}

	@Override
	public void run() {
		//content/Demo/us/en/articles-you should activate under child pages by using Scheduler code
		try(ResourceResolver resolver = demoUnitService.getResourceResolver()){
			PageManager pageManager = resolver.adaptTo(PageManager.class);
			Page page = pageManager.getPage("/content/Demo/us/en/articles");
			if(page!=null) {
				Iterator<Page> pages = page.listChildren();
				while(pages.hasNext()) {
					Page cpage =(Page) pages.next();
					String path = cpage.getPath();
					Session session = resolver.adaptTo(Session.class);
					replicator.replicate(session, ReplicationActionType.ACTIVATE, path);
					Log.info("Page Path: {}",path);
				}
			}

		} catch (ReplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
*/