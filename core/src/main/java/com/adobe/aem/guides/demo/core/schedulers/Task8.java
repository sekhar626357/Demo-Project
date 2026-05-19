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
@Designate(ocd=Task8Configuration.class)
public class Task8 implements Runnable{
	private static final Logger Log = LoggerFactory.getLogger(Task8.class);
	@Reference
	DemoUnitService demoUnitService;
	@Reference
	Replicator replicator;
	@Reference
	Scheduler scheduler;

	private boolean enabled;
	private String schedulerName;
	private String pageRootPath;

	@Activate
	public void activate(Task8Configuration config) {
		this.enabled = config.enable();
		this.schedulerName = config.scheduler_name();
		this.pageRootPath = config.page_root_path();

		if(enabled) {

			ScheduleOptions options = scheduler.EXPR(config.scheduler_expression());
			options.canRunConcurrently(false);
			options.name(schedulerName);
			scheduler.schedule(this, options);
		}else {
			scheduler.unschedule(schedulerName);
		}
	}

	@Override
	public void run() {
		Log.info("Inside run method...");
		//content/Demo/us/en
		try(ResourceResolver resolver = demoUnitService.getResourceResolver()){
			PageManager pageManager = resolver.adaptTo(PageManager.class);
			Page page = pageManager.getPage(pageRootPath);
			if(page!=null) {
				Iterator<Page> childPages = page.listChildren();
				while (childPages.hasNext()) {
					Page page2 = (Page) childPages.next();
					String pagePath = page2.getPath();
					Session session = resolver.adaptTo(Session.class);
					replicator.replicate(session, ReplicationActionType.ACTIVATE, pagePath);
					Log.info("PagePath: {}"+pagePath);
				}
			}
		} catch (ReplicationException e) {

			e.printStackTrace();
		}
	}

}
*/