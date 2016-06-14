package ddd.examples.servicelayerexample.servicelayer;

import ddd.examples.servicelayerexample.base.ServiceLayerCommandHandler;
import ddd.examples.servicelayerexample.domain.*;
import ddd.examples.servicelayerexample.infrastructure.ServiceLayerCommandsProcessingQueue;
import ddd.examples.servicelayerexample.infrastructure.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplyForLoanCommandHandlerV2 extends ServiceLayerCommandHandler<ApplyForLoanCommand, ApplyForLoanCommandResponse> {


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

            //Or domain event
            commandsQueue.process(new CreateLoanForClientServiceLayerCommand(command.clientId,
                                                                             command.loanParams(),
                                                                             loanApplication.getId()));
            return new ApplyForLoanCommandResponse("Your Loan is approved dude!!!");

        } else if (loanApplication.isRejected()){

            return new ApplyForLoanCommandResponse("Here is my middle finger!!! Loan rejected");

        } else {

            commandsQueue.process(new CreateTaskForAgentServiceLayerCommand(command.getClientId(), "Handle his application"));
            return new ApplyForLoanCommandResponse("We have to check you dude!!! Just wait!!!");

        }
    }
}
