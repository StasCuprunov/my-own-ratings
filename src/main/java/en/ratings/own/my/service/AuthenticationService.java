package en.ratings.own.my.service;

import en.ratings.own.my.dto.LoginDTO;
import en.ratings.own.my.dto.UserDTO;
import en.ratings.own.my.exception.WrongPasswordLoginException;
import en.ratings.own.my.exception.user.UserByEmailNotFoundException;
import en.ratings.own.my.model.User;
import en.ratings.own.my.model.role.RoleAssignment;
import en.ratings.own.my.service.repository.UserRepositoryService;
import en.ratings.own.my.service.repository.role.RoleAssignmentRepositoryService;
import en.ratings.own.my.service.repository.role.RoleRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static en.ratings.own.my.constant.PermissionConstants.LIST_OF_GRANTED_AUTHORITIES;

@Service
public class AuthenticationService {

    private final UserRepositoryService userRepositoryService;

    private final RoleAssignmentRepositoryService roleAssignmentRepositoryService;

    private final RoleRepositoryService roleRepositoryService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepositoryService userRepositoryService,
                                 RoleAssignmentRepositoryService roleAssignmentRepositoryService,
                                 RoleRepositoryService roleRepositoryService,
                                 PasswordEncoder passwordEncoder) {
        this.userRepositoryService = userRepositoryService;
        this.roleAssignmentRepositoryService = roleAssignmentRepositoryService;
        this.roleRepositoryService = roleRepositoryService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO login(LoginDTO loginDTO) throws Exception {
        String email = loginDTO.getEmail();
        User user = getUser(email);

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new WrongPasswordLoginException(email);
        }
        createSession(user);

        return new UserDTO(user);
    }

    private User getUser(String email) throws Exception {
        Optional<User> userResult = userRepositoryService.findByEmail(email);

        if (userResult.isEmpty()) {
            throw new UserByEmailNotFoundException(email);
        }
        return userResult.get();
    }

    private void createSession(User user) {
        SecurityContextHolder.getContext().setAuthentication(createToken(user));
    }

    private UsernamePasswordAuthenticationToken createToken(User user) {
        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(),
                getAuthorities(user.getId()));
    }

    private Collection<GrantedAuthority> getAuthorities(String userId) {
        Collection<GrantedAuthority> listOfAuthorities = new ArrayList<>();

        ArrayList<RoleAssignment> roleAssignments = roleAssignmentRepositoryService.findAllByUserId(userId);
        for (RoleAssignment roleAssignment: roleAssignments) {
            String roleName = roleRepositoryService.findById(roleAssignment.getRoleId()).get().getName();
            listOfAuthorities.add(LIST_OF_GRANTED_AUTHORITIES.get(roleName));
        }
        return listOfAuthorities;
    }
}
