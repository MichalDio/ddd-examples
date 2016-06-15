package ddd.examples.domain;

import ddd.examples.domain.base.AggregateRoot;
import ddd.examples.domain.base.Entity;
import ddd.examples.domain.services.RiskService;

public class LoanApplication extends AggregateRoot {
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

}
