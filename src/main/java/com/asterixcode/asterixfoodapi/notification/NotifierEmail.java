package com.asterixcode.asterixfoodapi.notification;

import com.asterixcode.asterixfoodapi.model.Client;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@NotifierType(UrgencyLevel.NOT_URGENT) // @Qualifier("email") // or @Primary
@Component
public class NotifierEmail implements Notifier {

    public NotifierEmail() {
        System.out.println("NotifierEmail REAL");
    }

    @Override
    public void notify(Client client, String message){
        System.out.printf("Notifying %s by email %s: %s\n",
                client.getName(), client.getEmail(), message);
    }
}
