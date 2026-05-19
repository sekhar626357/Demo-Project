package com.adobe.aem.guides.demo.core.models;

import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables=Resource.class,
		resourceType="/apps/Demo/components/dinesh/carousel")
public class Carousel1 {
	@ChildResource(name="multiField")
	private List<Resource> multiField;

	public List<Map<String,Object>> getMultiField() {
		List<Map<String,Object>> list=new ArrayList<>();
		
		if(multiField!=null) {
			for(Resource item : multiField) {
				Map<String,Object> map=new LinkedHashMap<>();
				map.put("textField",item.getValueMap().get("textField",""));
				map.put("pathField",item.getValueMap().get("pathField", ""));
				//For Nested MultiField
				List<Map<String,Object>> nlist=new ArrayList<>();
				Resource c=item.getChild("nestedMultifield");
				if(c!=null) {
					for(Resource item1:c.getChildren()) {
						Map<String,Object> nmap = new LinkedHashMap<>();
						nmap.put("nestedtextField", item1.getValueMap().get("nestedtextField",""));
						nlist.add(nmap);
					}
				}
				
				map.put("nestedMultifield", nlist);
				list.add(map);
			}
		}
		return list;
	}
}
