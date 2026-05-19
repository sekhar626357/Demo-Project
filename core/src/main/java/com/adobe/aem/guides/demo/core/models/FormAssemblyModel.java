package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.export.json.ComponentExporter;
import org.apache.sling.models.annotations.Exporter;

@Model(
        adaptables = Resource.class,
        resourceType = "Demo/components/FormAssemblyModel",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,   // "jackson"
        extensions = ExporterConstants.SLING_MODEL_EXTENSION  // "model.json"
)
public class FormAssemblyModel implements ComponentExporter {

    @ValueMapValue
    private String h2Heading;

    @ValueMapValue
    private String descriptionText;

    public String getH2Heading() {
        return h2Heading;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    @Override
    public String getExportedType() {
        return "Demo/components/FormAssemblyModel";
    }
}
