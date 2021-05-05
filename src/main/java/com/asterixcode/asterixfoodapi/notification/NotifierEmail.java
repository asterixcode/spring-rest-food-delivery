package com.asterixcode.asterixfoodapi.notification;

import com.asterixcode.asterixfoodapi.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("production")
@NotifierType(UrgencyLevel.NOT_URGENT) // @Qualifier("email") // or @Primary
@Component
public class NotifierEmail implements Notifier {

    @Autowired
    private NotifierProperties properties;

    @Override
    public void notify(Client client, String message){
        System.out.println("Host: " + properties.getServerHost());
        System.out.println("Port: " + properties.getServerPort());

        System.out.printf("Notifying %s by email %s: %s\n",
                client.getName(), client.getEmail(), message);
    }
}
