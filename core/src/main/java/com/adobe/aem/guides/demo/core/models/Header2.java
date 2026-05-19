package com.adobe.aem.guides.demo.core.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,
resourceType ="/apps/Demo/components/dinesh/header2",
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
		)
public class Header2 {

	@ValueMapValue	
	private String pathField;

	@ValueMapValue
	private String textField;

	@ChildResource
	private List<Resource> multiField;
	@ValueMapValue
	private Date articleExpiry;
	
	public boolean articleExpired = false;
	@ValueMapValue
	private boolean checkBox;

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
				Map<String,Object> mMap = new HashMap<String, Object>();
				mMap.put("mtextField", item.getValueMap().get("mtextField", String.class));
				mMap.put("datePicker", item.getValueMap().get("datePicker", String.class));

				mList.add(mMap);
			}
		}
		return mList;
	}

	public boolean getCheckBox() {
		return checkBox;
	}
	@PostConstruct
	public void init() {
		Date today = new Date();
		if(articleExpiry!=null && articleExpiry.compareTo(today)<0);
		articleExpired  = true;
	}


}
