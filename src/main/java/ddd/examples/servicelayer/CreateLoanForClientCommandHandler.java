package ddd.examples.servicelayer;

import ddd.examples.domain.Client;
import ddd.examples.domain.ClientRepository;
import ddd.examples.domain.Loan;
import ddd.examples.domain.LoanFactory;
import ddd.examples.infrastructure.Transactional;
import ddd.examples.servicelayer.base.ServiceLayerCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateLoanForClientCommandHandler extends ServiceLayerCommandHandler<CreateLoanForClientCommand,CreateLoanForClientCommandResponse>{

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    LoanFactory loanFactory;

    @Transactional
    public CreateLoanForClientCommandResponse execute(CreateLoanForClientCommand command) {

        Client client = clientRepository.findById(command.getClientId());

        Loan loan = loanFactory.create(command.getLoanParams(),command.getApplicationId());
        client.addLoan(loan);

        clientRepository.save(client);

        return new CreateLoanForClientCommandResponse();
    }
}
