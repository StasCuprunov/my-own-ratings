package en.ratings.own.my.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static en.ratings.own.my.constant.RoutingConstants.ROUTING_DEFAULT;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_START_PAGE;

@RestController
public class StartPageController {

    @GetMapping(value={ROUTING_DEFAULT, ROUTING_START_PAGE})
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
