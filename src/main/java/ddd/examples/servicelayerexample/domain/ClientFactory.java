package ddd.examples.servicelayerexample.domain;

import ddd.examples.servicelayerexample.servicelayer.CreateClientCommand;

public class ClientFactory {
    public Client create(CreateClientCommand command) {
        return new Client();
    }
}
