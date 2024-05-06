package en.ratings.own.my.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static en.ratings.own.my.constant.routing.RoutingConstants.ROUTING_DEFAULT;

@RestController
public class HelloController {

    @GetMapping(ROUTING_DEFAULT)
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
