package ddd.examples.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ClientRepository {

    private Map<Long,Client> clients = new HashMap<Long,Client>();

    public ClientRepository() {
    }

    public ClientRepository(Map<Long, Client> clients) {
        this.clients = clients;
    }

    public void save(Client client) {
        clients.put(client.getId(), client);
    }

    public Client findById(long clientId) {

        return clients.get(clientId);
    }
}
