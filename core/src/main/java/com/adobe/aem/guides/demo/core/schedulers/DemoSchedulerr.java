package com.adobe.aem.guides.demo.core.schedulers;


import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.AttributeDefinition;

import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.framework.Constants;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

@Component(immediate = true, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = DemoSchedulerr.Config.class)
public class DemoSchedulerr implements Runnable {

    @ObjectClassDefinition(name = "Demo Schedulerr Config")
    public @interface Config {
        @AttributeDefinition(name = "Cron Expression")
        String scheduler_expression() default "0 0/1 * 1/1 * ? *"; // every minute
    }

    private final AtomicBoolean running = new AtomicBoolean(false);

    @Activate
    @Modified
    protected void activate(Config config) {
        // No special activation logic
    }

    @Override
    public void run() {
        if (running.compareAndSet(false, true)) {
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                HttpGet request = new HttpGet("http://localhost:4502/bin/demo/update-jcr-content");
                client.execute(request); // Trigger servlet
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                running.set(false);
            }
        }
    }
}
