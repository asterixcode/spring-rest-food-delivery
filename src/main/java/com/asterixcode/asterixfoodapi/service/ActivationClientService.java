package com.asterixcode.asterixfoodapi.service;

import com.asterixcode.asterixfoodapi.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
public class ActivationClientService {
//    //@Qualifier("urgent")
//    @NotifierType(UrgencyLevel.URGENT)
//    @Autowired
//    private Notifier notifier;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void activate(Client client){
        client.activate();

        eventPublisher.publishEvent(new ClientActivatedEvent(client)); // tells the container that the client is active at this moment (with observer design pattern)

//        notifier.notify(client, "Your subscription is active!");
    }
}
