package ddd.examples.servicelayerexample.servicelayer;

import ddd.examples.servicelayerexample.base.ServiceLayerCommandHandler;
import ddd.examples.servicelayerexample.domain.*;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateClientCommandHandler extends ServiceLayerCommandHandler<CreateClientCommand,CreateClientCommandResponse> {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientFactory clientFactory;

    @Autowired
    private IdentificationService identificationService;


    public CreateClientCommandResponse execute(CreateClientCommand command) {

        Client client = clientFactory.create(command);

        //Client needs all the getters implemented
        IdentificationResult idResult = identificationService.identify(client);
        client.addIdentificationResult(idResult);

        clientRepository.save(client);

        //add something meaningful to the response
        return new CreateClientCommandResponse(client.getId());
    }

    public CreateClientCommandResponse alternativeExecute(CreateClientCommand command) {

        Client client = clientFactory.create(command);

        //Infra support for adding dependencies to services
        client.identify();
        clientRepository.save(client);

        //add something meaningful to the response
        return new CreateClientCommandResponse(client.getId());
    }

    public CreateClientCommandResponse oneMoreAlternativeAndTheBestExecute(CreateClientCommand command) {

        Client client = clientFactory.create(command);

        //Double Dispatch -> I don´t need getters
        client.identifyBy(identificationService);
        clientRepository.save(client);

        //add something meaningful to the response
        return new CreateClientCommandResponse(client.getId());
    }
}
