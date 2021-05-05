package com.asterixcode.asterixfoodapi.listener;

import com.asterixcode.asterixfoodapi.service.ClientActivatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InvoiceService {

    @EventListener
    public void clientActivatedListener(ClientActivatedEvent event){
        System.out.println("Issuing invoice for client: " + event.getClient().getName());
    }
}
