//create an OSGi configuration with a single text field called customMessage.
package com.adobe.aem.guides.demo.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/*@ObjectClassDefinition
 * @AttributeDefinition
 * @Designate 
 * These three annotations are related to dynamic configuration.
 */
//To define this interface for implementation class as configuration class.
@ObjectClassDefinition(
    name = "Custom Message Configuration",
    description = "Configuration for setting a custom message."
)
public @interface CustomMessageConfig {

    @AttributeDefinition(
        name = "Custom Message",
        description = "Enter your custom message"
    )
    String customMessage() default "Default custom message";
}
