package en.ratings.own.my.controller;

import en.ratings.own.my.dto.UserDTO;
import en.ratings.own.my.model.User;
import en.ratings.own.my.service.user.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static en.ratings.own.my.constant.routing.RoutingConstants.ROUTING_USER_CREATE;
import static en.ratings.own.my.constant.routing.RoutingConstants.ROUTING_USER_EMAIL;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(ROUTING_USER_EMAIL)
    public UserDTO findByEmail(@PathVariable @NonNull String email) throws Exception {
        return userService.findByEmail(email);
    }

    @PostMapping(ROUTING_USER_CREATE)
    public UserDTO create(@RequestBody User user) throws Exception {
        return userService.create(user);
    }
}
