package com.adobe.aem.guides.demo.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
@ObjectClassDefinition(name = "UserDetailsConfiguration",description = "About UserDetailsConfiguration")
public @interface UserDetailsConfiguration {

    @AttributeDefinition(
    name = "User_Name",
    description = "about user description",
    defaultValue = "Dinesh",
    type = AttributeType.STRING,
    required = true
    )
    public String userName();

    @AttributeDefinition(
    name = "User_ID",
    description = "about user id",
    defaultValue = "626357",
    type = AttributeType.INTEGER,
    required = true
    )
    public int userId();

    @AttributeDefinition(
    name = "User_Secret",
    description = "about user Secret",
    defaultValue = "Dinesh Secret",
    type = AttributeType.STRING,
    required = true
    )

    public String userSecret();

    @AttributeDefinition(
    name = "User_Api",
    description = "about user Api",
    defaultValue = "Dinesh Api",
    type = AttributeType.STRING,
    required = true
    )

    public String userApi();

}
