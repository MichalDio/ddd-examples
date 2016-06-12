package ddd.examples.servicelayerexample.domain;

public class LoanApplication extends EntityBase {
    public boolean isAccepted() {
        return true;
    }

    public boolean mustBeReviewedManually() {
        return true;
    }
}
