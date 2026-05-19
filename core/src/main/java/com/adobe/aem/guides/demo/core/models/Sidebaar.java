package com.adobe.aem.guides.demo.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class},
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
resourceType = {"/apps/Demo/components/dinesh/sidebar"})
public class Sidebaar {

	@ValueMapValue
	private String logoPath;

	@ValueMapValue
	private String logoMobileImage;

	@ValueMapValue
	private String logoLink;

	@ValueMapValue
	private boolean enableSwitch;

	@ChildResource
	private List<Resource> multiField;

	@ChildResource
	private List<Resource> multiField1;

	@ValueMapValue
	private String region;

	public String getLogoPath() {
		return logoPath;
	}

	public String getLogoMobileImage() {
		return logoMobileImage;
	}

	public String getLogoLink() {
		return logoLink;
	}

	public boolean isEnableSwitch() {
		return enableSwitch;
	}

	public List<Map<String, Object>> getMultiField() {
		List<Map<String, Object>> mList = new ArrayList<Map<String,Object>>();
		if(multiField!=null) {
			for(Resource item : multiField) {
				Map<String,Object> mMap = new HashMap<String, Object>();
				mMap.put("name", item.getValueMap().get("name", String.class));
				mMap.put("image", item.getValueMap().get("image", String.class));
				mList.add(mMap);
			}
		}
		return mList;
	}

	public List<Map<String,Object>> getMultiField1() {
		List<Map<String,Object>> mList = new ArrayList<Map<String,Object>>();
		if(multiField1!=null) {
			for(Resource item : multiField1) {
				Map<String,Object> mMap = new HashMap<String, Object>();
				mMap.put("desktopIcon", item.getValueMap().get("desktopIcon", String.class));
				mMap.put("mobileIcon", item.getValueMap().get("mobileIcon", String.class));
				
				List<Map<String,Object>> nList = new ArrayList<Map<String,Object>>();
				Resource c = item.getChild("nmultiField");
				if(c!=null) {
					for(Resource item1 : c.getChildren()) {
						Map<String,Object> nMap = new HashMap<String, Object>();
						nMap.put("navigationURL", item1.getValueMap().get("navigationURL", String.class));
						nList.add(nMap);
					}
				}
				mMap.put("nmultiField", nList);
				mList.add(mMap);

			}
		}
		return mList;
	}

	public String getRegion() {
		return region;
	}
}
