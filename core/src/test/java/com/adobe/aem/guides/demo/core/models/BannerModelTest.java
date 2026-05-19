package com.adobe.aem.guides.demo.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(AemContextExtension.class)
class BannerModelTest {

    private final AemContext context = new AemContext();
    private BannerModel model;

    @BeforeEach
    void setUp() {
        // load JSON into mock content path
        context.load().json("/models/banner.json", "/content");

        // adapt to model
        model = context.resourceResolver()
                       .getResource("/content") 
                       .adaptTo(BannerModel.class);
    }

    @Test
    void test() {
        assertEquals("Welcome Title", model.getTitle());
        assertEquals("Your Subtitle Here", model.getSubtitle());
        assertEquals("/contact-us", model.getCtaLink());
    }

}
