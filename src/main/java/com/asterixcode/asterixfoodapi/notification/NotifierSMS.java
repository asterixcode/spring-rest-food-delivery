package com.asterixcode.asterixfoodapi.notification;

import com.asterixcode.asterixfoodapi.model.Client;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Qualifier("urgent")
@NotifierType(UrgencyLevel.URGENT)
@Component
public class NotifierSMS implements Notifier {

    @Override
    public void notify(Client client, String message){
        System.out.printf("Notifying %s by SMS through the phone %s: %s\n",
                client.getName(), client.getPhone(), message);
    }
}
