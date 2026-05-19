package com.adobe.aem.guides.demo.core.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
@ExtendWith(AemContextExtension.class)
class BioDataModelTest {
	
	AemContext context = new AemContext();
	BioDataModel bioDataModel;
	
	@BeforeEach
	public void setUp() {
		
		/*context.addModelsForClasses(BioDataModel.class);
		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put("id", 8054);
		prop.put("name", "Sai");
		prop.put("age", 23);
		prop.put("email", "Sai@gmail.com");
		prop.put("gender", "Male");
		Resource saiResource = context.create().resource("/content/dummyLocation", prop);
		bioDataModel = saiResource.adaptTo(BioDataModel.class);*/
		
		/*context.addModelsForClasses(BioDataModel.class);
		context.load().json("/models/BioData.json", "/content");
		context.currentResource("/content");
		context.request().adaptTo(BioDataModel.class);*/
		
		//load JSON into mock content path
				context.load().json("/models/BioData.json", "/content");
		        

				//adapt to model
				bioDataModel = context.resourceResolver().getResource("/content").adaptTo(BioDataModel.class);
				
	}
	@Test
	void test() {
		assertEquals(8054, bioDataModel.getId());
		assertEquals("Sai", bioDataModel.getName());
		assertEquals(23, bioDataModel.getAge());
		assertEquals("Sai@gmail.com", bioDataModel.getEmail());
		assertEquals("Male", bioDataModel.getGender());
	}

}
