package com.asterixcode.asterixfoodapi.service;

import com.asterixcode.asterixfoodapi.model.Client;

public class ClientActivatedEvent {

    private Client client;

    public ClientActivatedEvent(Client client) {
        super();
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
