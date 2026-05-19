package com.adobe.aem.guides.demo.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition
public @interface ArticleConfiguration {
	
	@AttributeDefinition(name="REST API URL",
						description = "REST API URL to fetch article articls from third party.",
						type = AttributeType.STRING)
	public String articleRestURL()  default"https://gorest.co.in/public/v2/posts";
	
	
	@AttributeDefinition(name ="Enable/Disable  Configuration")
	public boolean status() default true;
	
	
}
