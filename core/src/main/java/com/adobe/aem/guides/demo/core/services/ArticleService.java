package com.adobe.aem.guides.demo.core.services;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Designate(ocd=ArticleConfiguration.class)
@Component(service=ArticleService.class,immediate=true)
public class ArticleService {
	public static final Logger Log = LoggerFactory.getLogger(ArticleService.class);
	private String articleRestURL;
	private boolean status;
	@Activate
	public void dinesh(ArticleConfiguration config) {
		Log.info("ArticleService-Dinesh method is @Activated...");
		this.articleRestURL=config.articleRestURL();
		this.status=config.status();
		Log.info("articleRestURL-{},status-{}",articleRestURL,status);

	}
	/*@Deactivate
	public void suresh(ArticleConfiguration config) {
		Log.info("ArticleService-Suresh method is @DeActivated");
	}*/
	@Modified
	public void update(ArticleConfiguration config) {
		Log.info("ArticleService-update method is executed");
		this.articleRestURL = config.articleRestURL();
		this.status = config.status();
		Log.info("articleRestURL-{},status-{}",articleRestURL,status);
		 String response=getAricles();
		 Log.info("Response From RestApI-{}",response);
	}
	// to fetch the data from the REST-API
	public String getAricles() {
		
		CloseableHttpClient client_object = HttpClients.createDefault();
		//When you open the browser one client_object will create.
		HttpGet request_object = new HttpGet (articleRestURL); 
		//when you hit the URL into browser immediately one request_Object will create.
		//in the request_Object it will add URL(url:https://gorest.co.in/public/v2/posts)
		String result = null;
		
		try {
			CloseableHttpResponse response = client_object.execute(request_object);
			HttpEntity entity =response.getEntity();
			if(entity != null) {
				result= EntityUtils.toString(entity);
			}
		} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}
	}

