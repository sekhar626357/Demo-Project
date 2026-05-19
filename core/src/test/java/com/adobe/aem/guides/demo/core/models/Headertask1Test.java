package com.adobe.aem.guides.demo.core.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
@ExtendWith(AemContextExtension.class)
class Headertask1Test {
	
	AemContext context = new AemContext();
	
	Headertask1 headertask1;
	
	
	@BeforeEach
	public void setup() throws Exception {
	    context.addModelsForClasses(Headertask1.class);

	    // Create the parent resource first
	    Map<String, Object> prop = new HashMap<>();
	    prop.put("pathField", "/content/dam/Demo/asset.jpg");
	    prop.put("textField", "Enter Some Text here");
	    prop.put("checkBox", true);
	    prop.put("text", "Hi");
	    context.create().resource("/content/header", prop);

	    // Then create children under the correct path
	    Map<String, Object> childProps1 = new HashMap<>();
	    childProps1.put("mtextField", "Child Text 1");
	    childProps1.put("datePicker", "2025-04-29");
	    context.create().resource("/content/header/multiField/item1", childProps1);

	    Map<String, Object> childProps2 = new HashMap<>();
	    childProps2.put("mtextField", "Child Text 2");
	    childProps2.put("datePicker", "2025-04-30");
	    context.create().resource("/content/header/multiField/item2", childProps2);

	    // Adapt after all resources are created
	    Resource articleResource = context.resourceResolver().getResource("/content/header");
	    headertask1 = articleResource.adaptTo(Headertask1.class); 
	}
	
	@Test
	void testMultiFieldChildResources() {
	    List<Map<String, Object>> multiField = headertask1.getMultiField();
	    assertEquals(2, multiField.size());

	    Map<String, Object> firstItem = multiField.get(0);
	    assertEquals("Child Text 1", firstItem.get("mtextField"));
	    assertEquals("2025-04-29", firstItem.get("datePicker"));

	    Map<String, Object> secondItem = multiField.get(1);
	    assertEquals("Child Text 2", secondItem.get("mtextField"));
	    assertEquals("2025-04-30", secondItem.get("datePicker"));
	}


}
