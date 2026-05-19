package com.adobe.aem.guides.demo.core.services;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate=true)
public class PressRealeseService {
	public static final Logger Log = LoggerFactory.getLogger(ArticleService.class);
	
	@Reference
	ArticleService articleService;
	
	@Activate
	public void dinesh() {
		 String c=articleService.getAricles();
		 Log.info("Response-{}",c);
		Log.info("PressRealeseService-Dinesh method is @Activated...");
	}
	@Deactivate
	public void suresh() {
		Log.info("PressRealeseService-Suresh method is @DeActivated");
	}
	@Modified
	public void update() {
		Log.info("PressRealeseService-update method is executed");
	}
}
