package ddd.examples.domain;

import org.springframework.stereotype.Component;

@Component
public class LoanFactory {
    public Loan create(String loanParams, long applicationId) {
        return new Loan();
    }
}
