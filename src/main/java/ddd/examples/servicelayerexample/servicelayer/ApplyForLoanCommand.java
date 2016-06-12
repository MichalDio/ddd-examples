package ddd.examples.servicelayerexample.servicelayer;

import ddd.examples.servicelayerexample.base.ServiceLayerCommand;

public class ApplyForLoanCommand extends ServiceLayerCommand {
    public long getClientPersonalNumber() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long clientId;
}
