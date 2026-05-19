package com.adobe.aem.guides.demo.core.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
resourceType = {"/apps/Demo/components/task/header"})
public class Headertask1 {
	@ValueMapValue
	private String pathField;
	@ValueMapValue
	private String textField;
	@ChildResource(name="multiField")
	private List<Resource> multiField;
	@ValueMapValue
	private boolean checkBox;
	@ValueMapValue(name = "sling:resourceType")
	private String slingResourceType;
	@ValueMapValue
	private String text;
	
	public String getText() {
		return text;
	}
	public boolean getCheckBox() {
		return checkBox;
	}
	public String getPathField() {
		return pathField;
	}
	public String getTextField() {
		return textField;
	}
	public List<Map<String, Object>> getMultiField() {
		List<Map<String, Object>> mList = new ArrayList<Map<String,Object>>();
		if(multiField!=null) {
			for(Resource item : multiField) {
			Map<String, Object> mMap = new LinkedHashMap<String, Object>();
				mMap.put("mtextField", item.getValueMap().get("mtextField", String.class));
				mMap.put("datePicker", item.getValueMap().get("datePicker", String.class));
				mList.add(mMap);
			}
		}
		return mList;
	}
	
}
