package ddd.examples;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("request")
@RequestMapping(value = "/ping")
public class PingController {

    @RequestMapping(method =  RequestMethod.GET)
    public ResponseEntity<String> pingGet() {
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
