package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BioDataModel {
 
	 @ValueMapValue
	 private int id;
	 
	 @ValueMapValue
	 private String name;
	 
	 @ValueMapValue
	 private int age;
	 
	 @ValueMapValue
	 private String email;
	 
	 @ValueMapValue
	 private String gender;

	public int getId() {
		return id;
	}

	

	public String getName() {
		return name;
	}

	

	public int getAge() {
		return age;
	}

	

	public String getEmail() {
		return email;
	}

	

	public String getGender() {
		return gender;
	}
	 	 
}
