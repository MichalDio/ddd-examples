package ddd.examples.domain;

import ddd.examples.servicelayer.commands.CreateClientCommand;
import org.springframework.stereotype.Component;

@Component
public class ClientFactory {
    public Client create(CreateClientCommand command) {
        return new Client();
    }
}
