package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(AemContextExtension.class)
class TestingModelTest {

    private TestingModel model;
    private TestingModel emptyModel;

    @BeforeEach
    public void setup(AemContext context) {
        // Create resource with configured values
        Resource resource = context.create().resource("/content/testing",
                "textField", "Sample Text",
                "checkboxField", true);
        model = resource.adaptTo(TestingModel.class);

        // Create resource with no/default values
        Resource emptyResource = context.create().resource("/content/empty");
        emptyModel = emptyResource.adaptTo(TestingModel.class);
    }

    @Test
    void testGetTextField() {
        assertNotNull(model);
        assertEquals("Sample Text", model.getTextField());
        assertNull(emptyModel.getTextField());
    }

    @Test
    void testIsCheckboxField() {
        assertNotNull(model);
        assertTrue(model.isCheckboxField());
        assertFalse(emptyModel.isCheckboxField());
    }
}
