package ddd.examples.servicelayer.commands;

import ddd.examples.servicelayer.base.ServiceLayerCommand;

public class CreateLoanForClientCommand extends ServiceLayerCommand {

    private final long clientId;
    private final String loanParams;
    private final long applicationId;

    public CreateLoanForClientCommand(long clientId, String loanParams, long applicationId) {

        this.clientId = clientId;
        this.loanParams = loanParams;
        this.applicationId = applicationId;
    }

    public long getClientId() {
        return clientId;
    }

    public String getLoanParams() {
        return loanParams;
    }

    public long getApplicationId() {
        return applicationId;
    }
}
