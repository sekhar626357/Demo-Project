package com.adobe.aem.guides.demo.core.models;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.demo.core.services.ArticleService;
import com.adobe.cq.export.json.ExporterConstants;
@Exporter(extensions = ExporterConstants.SLING_MODEL_EXTENSION ,
		  name = ExporterConstants.SLING_MODEL_EXPORTER_NAME)
@Model(adaptables = Resource.class,
resourceType="Demo/components/dinesh/header1",
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Header1 {
	@ValueMapValue(injectionStrategy = InjectionStrategy.REQUIRED)
	private String pathField;

	@ValueMapValue
	private String textField;

	@ValueMapValue
	private String checkBox;

	@ChildResource
	private List<Resource> multiField;

	public List<Map<String, String>> getMultiField() {
		List<Map<String, String>> listObj = new ArrayList<>();

		if (multiField != null) {
			for (Resource items : multiField) {

				Map<String, String> mapObj = new LinkedHashMap<>();
				mapObj.put("mtextField", items.getValueMap().get("mtextField", ""));
				mapObj.put("datePicker", items.getValueMap().get("datePicker", ""));           
				listObj.add(mapObj);
			}
		}

		return listObj;
	}


	public String getPathField() {
		return pathField;
		
	}

	public String getTextField() {
		return textField;
	}

	public String getCheckBox() {
		return checkBox;
	}

	@Self
	private SlingHttpServletRequest request;
	@Self
	private Resource resource;

	public Header m1() {

		return resource.adaptTo(Header.class);

	}
	public String m2() {
		Header c= resource.adaptTo(Header.class);
		c.Dinesh();
		c.getMultiField();
		return null;
	}
	@ValueMapValue(name="sling:resourceType")
	@Default(values="Demo/components/dinesh/header1")
	private String slingresourceType;
	
	@OSGiService
	private ArticleService articleService;
	
	public void num() {
		articleService.dinesh(null);
	}
	

}
