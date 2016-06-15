package ddd.examples.domain;

public class LoanFactory {
    public Loan create(String loanParams, long applicationId) {
        return new Loan();
    }
}
