package ddd.examples.servicelayer;

import ddd.examples.domain.Client;
import ddd.examples.domain.ClientRepository;
import ddd.examples.domain.Loan;
import ddd.examples.domain.LoanFactory;
import ddd.examples.infrastructure.Transactional;
import ddd.examples.servicelayer.base.ServiceLayerCommandHandler;
import ddd.examples.servicelayer.commands.CreateLoanForClientCommand;
import ddd.examples.servicelayer.commands.CreateLoanForClientCommandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CreateLoanForClientCommandHandler extends ServiceLayerCommandHandler<CreateLoanForClientCommand,CreateLoanForClientCommandResponse>{

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    LoanFactory loanFactory;

    @Transactional
    public CreateLoanForClientCommandResponse execute(CreateLoanForClientCommand command) {

        Client client = clientRepository.findById(command.getClientId());
        if(client != null){
            Loan loan = loanFactory.create(command.getLoanParams(),command.getApplicationId());
            client.addLoan(loan);

            clientRepository.save(client);
        }else{
            //No client in request
            //TODO handling
        }



        return new CreateLoanForClientCommandResponse();
    }
}
