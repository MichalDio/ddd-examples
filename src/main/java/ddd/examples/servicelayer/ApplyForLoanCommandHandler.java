package ddd.examples.servicelayer;

import ddd.examples.domain.services.RiskService;
import ddd.examples.servicelayer.base.ServiceLayerCommandHandler;
import ddd.examples.domain.*;
import ddd.examples.infrastructure.ServiceLayerCommandsProcessingQueue;
import ddd.examples.infrastructure.Transactional;
import ddd.examples.servicelayer.commands.ApplyForLoanCommand;
import ddd.examples.servicelayer.commands.ApplyForLoanCommandResponse;
import ddd.examples.servicelayer.commands.CreateLoanForClientCommand;
import ddd.examples.servicelayer.commands.CreateTaskForAgentrCommand;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplyForLoanCommandHandler extends ServiceLayerCommandHandler<ApplyForLoanCommand, ApplyForLoanCommandResponse> {


    @Autowired
    private LoanApplicationFactory loanApplicationFactory;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private RiskService riskService;

    @Autowired
    private ServiceLayerCommandsProcessingQueue commandsQueue;

    @Transactional
    public ApplyForLoanCommandResponse execute(ApplyForLoanCommand command) {

        LoanApplication loanApplication = loanApplicationFactory.create(command);
        //Client Id (not reference) is attribute of LoanApplication

        loanApplication.assessRiskUsing(riskService);
        //Risk assessment result is stored as a part of loan application

        loanApplicationRepository.save(loanApplication);

        if (loanApplication.isAccepted()) {

            //Assumption is that Commands Queue have to be persistent and inserting of command to the queue is atomic with changes on LoanApplication aggregate.
            //Processing of CreateLoanForClientCommand have to be atomic with marking a queue item as processed. When processing fails queue item should be in some error state
            // or sent to death letter channel and after fix should be processed to get to consistent state.
            commandsQueue.process(new CreateLoanForClientCommand(command.clientId,
                                                                             command.loanParams(),
                                                                             loanApplication.getId()));
            return new ApplyForLoanCommandResponse("Your Loan is approved dude!!!");

        } else if (loanApplication.isRejected()){

            return new ApplyForLoanCommandResponse("Here is my middle finger!!! Loan rejected");

        } else {

            commandsQueue.process(new CreateTaskForAgentrCommand(command.getClientId(), "Handle his application"));
            return new ApplyForLoanCommandResponse("We have to check you dude!!! Just wait!!!");

        }
    }
}
