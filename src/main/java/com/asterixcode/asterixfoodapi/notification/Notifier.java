package com.asterixcode.asterixfoodapi.notification;

import com.asterixcode.asterixfoodapi.model.Client;

public interface Notifier {
    void notify(Client client, String message);
}
