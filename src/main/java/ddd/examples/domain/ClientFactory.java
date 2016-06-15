package ddd.examples.domain;

import ddd.examples.servicelayer.CreateClientCommand;

public class ClientFactory {
    public Client create(CreateClientCommand command) {
        return new Client();
    }
}
