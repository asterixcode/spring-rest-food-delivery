package com.asterixcode.asterixfoodapi.listener;

import com.asterixcode.asterixfoodapi.notification.Notifier;
import com.asterixcode.asterixfoodapi.notification.NotifierType;
import com.asterixcode.asterixfoodapi.notification.UrgencyLevel;
import com.asterixcode.asterixfoodapi.service.ClientActivatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {

    @NotifierType(UrgencyLevel.NOT_URGENT)
    @Autowired
    private Notifier notifier;

    @EventListener
    public void clientActivatedListener(ClientActivatedEvent event){
        notifier.notify(event.getClient(), "Your subscription is active!");
    }
}
