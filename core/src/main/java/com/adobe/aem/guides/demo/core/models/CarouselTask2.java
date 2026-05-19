package com.adobe.aem.guides.demo.core.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = {Resource.class})
public class CarouselTask2 {
	@ChildResource(name="multiField")
	private List<Resource> multiField;

	public List<Map<String, Object>> getMultiField() {
		List<Map<String, Object>> mList = new ArrayList<Map<String,Object>>();
		if(multiField!=null) {
			for(Resource item : multiField) {
				Map<String,Object> mMap = new LinkedHashMap<String, Object>();

				List<Map<String,Object>> nList = new ArrayList<Map<String,Object>>();
				Resource c = item.getChild("nestedMultiField");
				if(c!=null) {
					for(Resource item1 : c.getChildren()) {
						Map<String,Object> nMap = new LinkedHashMap<String,Object>();
						nMap.put("textField", item1.getValueMap().get("textField", String.class));
						nMap.put("pathField", item1.getValueMap().get("pathField", String.class));

						List<Map<String,Object>> inList = new ArrayList<Map<String,Object>>();
						Resource ci = item1.getChild("multiField1");
						if(ci!=null) {
							for(Resource item2 : ci.getChildren()) {
								Map<String,Object> inMap = new LinkedHashMap<String,Object>();
								inMap.put("mtextField", item2.getValueMap().get("mtextField", String.class));
								inList.add(inMap);
							}
						}
						nMap.put("multiField1", inList);
						nList.add(nMap);
					}
				}

				mMap.put("nestedMultiField", nList);
				mList.add(mMap);
			}
		}
		return mList;
	} 

}
