package com.adobe.aem.guides.demo.core.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables=Resource.class,resourceType="Demo/components/dinesh/my-carousel")
public class MyCarousel {

	@ChildResource(name="multiField")
	private List<Resource> multiField;
	
	public List<Map<String,Object>> getMultiField(){
		List<Map<String,Object>> mlist = new ArrayList<>();
		if(multiField!=null){
		for(Resource item:multiField){
			Map<String,Object> mmap = new LinkedHashMap<>();
			List<Map<String,Object>> nlist = new ArrayList<>();
			Resource c = item.getChild("nestedMultifield");
				if(c!=null){
				for(Resource item1:c.getChildren()){
				Map<String,Object> nmap = new LinkedHashMap<>();
				nmap.put("nestedtextField",item1.getValueMap().get("nestedtextField",""));
				nlist.add(nmap);
				}

				}
				mmap.put("nestedMultifield",nlist);
				mlist.add(mmap);
				
		}

	}
					return mlist;
	}
}
