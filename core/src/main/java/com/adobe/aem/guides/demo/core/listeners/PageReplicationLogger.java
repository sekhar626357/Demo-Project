package com.adobe.aem.guides.demo.core.listeners;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationActionType;

@Component(
    service = EventHandler.class,
    immediate = true,
    property = {
        EventConstants.EVENT_TOPIC + "=" + ReplicationAction.EVENT_TOPIC,
        EventConstants.EVENT_FILTER + "=(&(paths=/content/Demo/us/en/page1/page2/*))"
    }
)
public class PageReplicationLogger implements EventHandler {

    private static final Logger log = LoggerFactory.getLogger(PageReplicationLogger.class);

    @Override
    public void handleEvent(Event event) {
        ReplicationAction action = ReplicationAction.fromEvent(event);
        if (action != null) {
            String path = action.getPath();
            ReplicationActionType type = action.getType();

            
            if (ReplicationActionType.ACTIVATE.equals(type)) {
                log.info("Page Published (Activated): {}", path);
            } else if (ReplicationActionType.DEACTIVATE.equals(type)) {
                log.info("Page Unpublished (Deactivated): {}", path);
            }
        }
    }
}
