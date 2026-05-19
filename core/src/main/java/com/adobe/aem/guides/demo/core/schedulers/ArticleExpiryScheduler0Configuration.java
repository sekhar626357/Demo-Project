package com.adobe.aem.guides.demo.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition
public @interface ArticleExpiryScheduler0Configuration {
	@AttributeDefinition(name = "Enable/Disable Scheduler")
	public boolean enable() default true;
	@AttributeDefinition(name = "Scheduler Name")
	public String schedulerName() default "SchedulerName";
	@AttributeDefinition(name="Scheduler Corn Expression")
	public String cronExpression() default "*/5 * * ? * *";
}
