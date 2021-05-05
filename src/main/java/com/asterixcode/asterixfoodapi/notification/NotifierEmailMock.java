package com.asterixcode.asterixfoodapi.notification;

import com.asterixcode.asterixfoodapi.model.Client;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@NotifierType(UrgencyLevel.NOT_URGENT)
@Component
public class NotifierEmailMock implements Notifier {

    public NotifierEmailMock() {
        System.out.println("NotifierEmail MOCK");
    }

    @Override
    public void notify(Client client, String message){
        System.out.printf("MOCK: Notification would be sent to %s by the email %s: %s\n",
                client.getName(), client.getEmail(), message);
    }
}
