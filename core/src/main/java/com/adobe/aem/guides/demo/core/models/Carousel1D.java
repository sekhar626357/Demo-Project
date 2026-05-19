package com.adobe.aem.guides.demo.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = {Resource.class},
		resourceType = "/apps/Demo/components/dinesh/Carousel1",
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Carousel1D {

	@ChildResource(name="multiField")
	private List<Resource> multiField;

	public List<Map<String, Object>> getMultiField() {
		List<Map<String, Object>> mList = new ArrayList<Map<String,Object>>();
		if(multiField!=null) {
			for(Resource item : multiField) {
				Map<String,Object> mMap = new HashMap<String, Object>();
				mMap.put("mtextField", item.getValueMap().get("mtextField", String.class));
				mMap.put("mpathField", item.getValueMap().get("mpathField", String.class));
				
				List<Map<String,Object>> nList = new ArrayList<Map<String,Object>>();
				Resource c = item.getChild("nmultiField");
				if(c!=null) {
					for(Resource item1 : c.getChildren()) {
						Map<String,Object> nMap = new HashMap<String, Object>();
						nMap.put("ntextField",item1.getValueMap().get("ntextField", String.class));
						nList.add(nMap);
					}
					
				}
				mMap.put("nmultiField", nList);
				mList.add(mMap);
			}
		}
		return mList;
	}
	
	
}
