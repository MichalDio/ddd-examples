package ddd.examples.infrastructure;

import ddd.examples.servicelayer.CreateLoanForClientCommandHandler;
import ddd.examples.servicelayer.commands.CreateLoanForClientCommand;
import ddd.examples.servicelayer.commands.CreateLoanForClientCommandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by michal.dio on 17/06/2016.
 */
@RestController
@Scope("request")
@RequestMapping(value = "/loans")
public class LoansController {

    @Autowired
    private CreateLoanForClientCommandHandler loanHandler;

    @RequestMapping(value = "/getLoan", method = RequestMethod.GET)
    public ResponseEntity<String> loanGet() {
        System.out.println("getting loan...");
        System.out.println(loanHandler.toString());
        CreateLoanForClientCommand command = new CreateLoanForClientCommand(1, "params", 1);
        CreateLoanForClientCommandResponse response = loanHandler.execute(command);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> defaults() {
        System.out.println("default method ...");
        return new ResponseEntity<String>(HttpStatus.OK);
    }


}
