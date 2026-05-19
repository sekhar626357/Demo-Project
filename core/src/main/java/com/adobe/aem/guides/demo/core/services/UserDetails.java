package com.adobe.aem.guides.demo.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = UserDetails.class,immediate = true,enabled = true)
@Designate(ocd=UserDetailsConfiguration.class)
public class UserDetails {

    private static final Logger LOG =LoggerFactory.getLogger(UserDetails.class);

    private String userName;

    private int userId;

    private String userSecret;

    private String userApi;

    @Activate
    public void activate(UserDetailsConfiguration userDetailsConfiguration){
            update(userDetailsConfiguration);
            LOG.info("Data  coming UserDetails class-->Activated");
    }

    @Deactivate
    public void deactivate(UserDetailsConfiguration userDetailsConfiguration){

        LOG.info("Data  coming UserDetails class--> Deactivated");
    }

    @Modified
    public void  modified(UserDetailsConfiguration userDetailsConfiguration){

        LOG.info("Data  coming UserDetails class --> Modified");

    }
    public void update(UserDetailsConfiguration userDetailsConfiguration){

        this.userName=userDetailsConfiguration.userName(); // instance variables initilization

        this.userId=userDetailsConfiguration.userId(); 

        this.userSecret=userDetailsConfiguration.userSecret();

        this.userApi=userDetailsConfiguration.userApi();

        LOG.info("userName{}, userId{}, UserSecret{}, userApi{}",userName ,userId, userSecret, userApi);
    }
}
