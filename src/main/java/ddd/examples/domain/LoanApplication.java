package ddd.examples.domain;

import ddd.examples.domain.services.RiskService;

public class LoanApplication extends EntityBase {
    private boolean rejected;

    public boolean isAccepted() {
        return true;
    }

    public boolean mustBeReviewedManually() {
        return true;
    }

    public void assessRiskUsing(RiskService riskService) {

    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }
}
