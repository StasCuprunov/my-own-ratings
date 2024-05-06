package en.ratings.own.my.controller;

import en.ratings.own.my.dto.UserDTO;
import en.ratings.own.my.model.User;
import en.ratings.own.my.service.user.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static en.ratings.own.my.constant.PermissionConstants.HAS_ROLE_ADMIN_PERMISSION;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_CREATE;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_EMAIL;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_USER;
import static en.ratings.own.my.utility.ResponseEntityUtility.createCreatedResponseEntity;
import static en.ratings.own.my.utility.ResponseEntityUtility.createOkResponseEntity;

@RestController
@RequestMapping(ROUTING_USER)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize(HAS_ROLE_ADMIN_PERMISSION)
    @GetMapping(ROUTING_EMAIL)
    public ResponseEntity<UserDTO> findByEmail(@PathVariable @NonNull String email) throws Exception {
        return createOkResponseEntity(userService.findByEmail(email));
    }

    @PostMapping(ROUTING_CREATE)
    public ResponseEntity<UserDTO> create(@RequestBody User user) throws Exception {
        return createCreatedResponseEntity(userService.create(user));
    }
}
