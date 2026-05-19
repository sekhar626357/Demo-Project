package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SaiModel {
	
	@ValueMapValue
	private String movieName;
	
	@ValueMapValue
	private int ticketPrice;
	
	@ValueMapValue
	private String movieCertificate;
	

	public String getTextField1() {
		return movieName;
	}
	
	public int getTextField2() {
		return ticketPrice;
	}
	public String getTextField3() {
		return movieCertificate;
	}
	
}
