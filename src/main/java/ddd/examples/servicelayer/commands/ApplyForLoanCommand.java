package ddd.examples.servicelayer.commands;

import ddd.examples.servicelayer.base.ServiceLayerCommand;

public class ApplyForLoanCommand extends ServiceLayerCommand {
    public long clientId;

    public long getClientId() {
        return clientId;
    }

    public String loanParams() {
        return "loanParams";
    }
}
