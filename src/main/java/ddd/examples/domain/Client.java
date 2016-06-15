package ddd.examples.domain;

import ddd.examples.domain.base.AggregateRoot;
import ddd.examples.domain.services.IdentificationService;

public class Client extends AggregateRoot {

    public void addLoan(Loan loan) {

    }

    public void addIdentificationResult(IdentificationResult idResult) {
    }

    public void identify() {
    }

    public void identifyBy(IdentificationService identificationService) {
    }
}
