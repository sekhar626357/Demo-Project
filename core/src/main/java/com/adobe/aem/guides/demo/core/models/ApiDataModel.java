package com.adobe.aem.guides.demo.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Model(
        adaptables = Resource.class,
        adapters = ApiDataModel.class,
        resourceType = "aemgeeks/components/singlearticle123",   // 🔥 IMPORTANT
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ApiDataModel {

    @ValueMapValue
    private String apiUrl;

    private String apiResponse;

    public String getApiResponse() {
        return apiResponse;
    }

    @PostConstruct
    protected void init() {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            apiResponse = sb.toString();
        } catch (Exception e) {
            apiResponse = "Error fetching data: " + e.getMessage();
        }
    }
}
