package com.asterixcode.asterixfoodapi.notification;

import com.asterixcode.asterixfoodapi.model.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@NotifierType(UrgencyLevel.NOT_URGENT) // @Qualifier("email") // or @Primary
@Component
public class NotifierEmail implements Notifier {

    @Value("${notifier.email.server-host}")
    private String host;

    @Value("${notifier.email.server-port}")
    private Integer port;

    @Override
    public void notify(Client client, String message){
        System.out.println("Host: " + host);
        System.out.println("Port: " + port);

        System.out.printf("Notifying %s by email %s: %s\n",
                client.getName(), client.getEmail(), message);
    }
}
