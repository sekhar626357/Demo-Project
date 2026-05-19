package com.adobe.aem.guides.demo.core.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = {Resource.class},
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
resourceType = {"apps/Deom/copmonents/Dinesh/sidebar"})
public class Practice01 {
	@ChildResource(name="multiField")
	private List<Resource> multiField;

	@ChildResource(name="multiField1")
	private List<Resource> multiField1;
	
	@ChildResource(name="multiField2")
	private List<Resource> multiField2;

	public List<Map<String, Object>> getMultiField(){
		List<Map<String, Object>> mList = new ArrayList<>();
		if(multiField!=null) {
			for(Resource item : multiField) {
				Map<String,Object> mMap = new LinkedHashMap<String, Object>();
				mMap.put("mTextField", item.getValueMap().get("mTextField",""));
				mList.add(mMap);
			}
		}
		return mList;
	}
	public List<Map<String,Object>> getMultiField1(){
		List<Map<String,Object>> mList = new ArrayList<Map<String,Object>>();
		if(multiField1!=null) {
			for(Resource item:multiField1) {
				Map<String,Object> mMap = new LinkedHashMap<>();
				mMap.put("mtextField", item.getValueMap().get("mTextField",""));
				List<Map<String,Object>> nList = new ArrayList<Map<String,Object>>();
				Resource c = item.getChild("nMultiField");
				if(c!=null) {
					for(Resource item1:c.getChildren()) {
						Map<String,Object> nMap = new LinkedHashMap<>();
						nMap.put("nTextField", item1.getValueMap().get("ntextField",""));
						nList.add(nMap);
					}
				}
				mMap.put("nMultiField", nList);
				mList.add(mMap);
			}
		}
		return mList;
	}
	public List<Map<String,Object>> getMultiField2(){
		List<Map<String,Object>> mList = new ArrayList<Map<String,Object>>();
		if(multiField2!=null) {
			for(Resource item:multiField2) {
				Map<String,Object> mMap = new LinkedHashMap<String, Object>();
				List<Map<String,Object>> nList = new ArrayList<Map<String,Object>>();
			     Resource c = item.getChild("nmultiField");
			     if(c!=null) {
			    	 for(Resource item1:c.getChildren()) {
							Map<String,Object> nMap = new LinkedHashMap<String, Object>();
							nMap.put("nTextField", item1.getValueMap().get("ntextField",""));
							nList.add(mMap);
			    	 }
			     }
			     mMap.put("nmultiField", nList);
			     mList.add(mMap);
			}
		}
		return mList;
	}
}
