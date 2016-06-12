package ddd.examples.servicelayerexample.servicelayer;

import ddd.examples.servicelayerexample.base.ServiceLayerCommandHandlerBase;
import ddd.examples.servicelayerexample.domain.*;
import ddd.examples.servicelayerexample.infrastructure.ServiceLayerCommandsProcessingQueue;
import ddd.examples.servicelayerexample.infrastructure.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplyForLoanCommandHandler extends ServiceLayerCommandHandlerBase<ApplyForLoanCommand, ApplyForLoanCommandResponse> {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LoanApplicationFactory loanApplicationFactory;

    @Autowired
    private LoanFactory loanFactory;

    @Autowired
    private RiskAssessmentService riskAssessmentService;

    @Autowired
    private ServiceLayerCommandsProcessingQueue commandsQueue;

    @Transactional
    public ApplyForLoanCommandResponse execute(ApplyForLoanCommand command) {

        Client client = clientRepository.findByPersonalNumber(command.getClientPersonalNumber());
        if (client == null) {
            client = new Client();
        }

        LoanApplication loanApplication = loanApplicationFactory.create(command);
        client.addLoanApplication(loanApplication);

        riskAssessmentService.performRiskAssessment(loanApplication);

        if (loanApplication.isAccepted()) {

            Loan loan = loanFactory.create(loanApplication);
            client.addLoan(loan);
            return new ApplyForLoanCommandResponse();

        } else if (loanApplication.mustBeReviewedManually()) {
            commandsQueue.process(new CreateTaskServiceLayerCommand(client.getId(), "Handle his application"));
            return new ApplyForLoanCommandResponse();
        } else {
            //show the middle finger
            return new ApplyForLoanCommandResponse();
        }
    }
}
