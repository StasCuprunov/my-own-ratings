package en.ratings.own.my.service.user;

import en.ratings.own.my.dto.UserDTO;
import en.ratings.own.my.exception.user.creation.UserCreationFailedException;
import en.ratings.own.my.model.User;
import en.ratings.own.my.model.role.Role;
import en.ratings.own.my.model.role.RoleAssignment;
import en.ratings.own.my.service.repository.UserRepositoryService;
import en.ratings.own.my.service.repository.role.RoleAssignmentRepositoryService;
import en.ratings.own.my.service.repository.role.RoleRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static en.ratings.own.my.utility.EnumUtility.roleUserAsString;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_EMAIL_ALREADY_EXISTS;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_EMAIL_SYNTAX;
import static en.ratings.own.my.service.user.UserValidation.isEmailSyntaxAllowed;
import static en.ratings.own.my.service.user.UserValidation.passwordValidation;
import static en.ratings.own.my.utility.StringUtility.addExistentStringToArrayList;

@Service
public class UserService {

    private final UserRepositoryService userRepositoryService;

    private final RoleAssignmentRepositoryService roleAssignmentRepositoryService;

    private final RoleRepositoryService roleRepositoryService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepositoryService userRepositoryService,
                       RoleAssignmentRepositoryService roleAssignmentRepositoryService,
                       RoleRepositoryService roleRepositoryService, PasswordEncoder passwordEncoder) {
        this.userRepositoryService = userRepositoryService;
        this.roleAssignmentRepositoryService = roleAssignmentRepositoryService;
        this.roleRepositoryService = roleRepositoryService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO findByEmail(String email) throws Exception {
        User user = userRepositoryService.findByEmail(email);
        return convertModelToDTO(user);
    }

    public UserDTO create(User user) throws Exception {
        ArrayList<String> keysForException = emailValidation(user.getEmail());
        keysForException = addExistentStringToArrayList(keysForException, passwordValidation(user.getPassword()));

        if (!keysForException.isEmpty()) {
            throw new UserCreationFailedException(keysForException);
        }
        return createUser(user);
    }

    private UserDTO createUser(User user) throws Exception {
        encodePassword(user);
        User userResult = userRepositoryService.save(user);
        String roleName = roleUserAsString();
        Role role = roleRepositoryService.findByName(roleName);

        roleAssignmentRepositoryService.save(new RoleAssignment(userResult.getId(), role.getId()));

        return convertModelToDTO(userResult);
    }

    private ArrayList<String> emailValidation(String email) {
        ArrayList<String> keysForException = new ArrayList<>();
        if (!isEmailSyntaxAllowed(email)) {
            keysForException.add(KEY_EMAIL_SYNTAX);
        }
        if (!isEmailAvailable(email)) {
            keysForException.add(KEY_EMAIL_ALREADY_EXISTS);
        }
        return keysForException;
    }

    private boolean isEmailAvailable(String email) {
        try {
            userRepositoryService.findByEmail(email);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    private void encodePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    private UserDTO convertModelToDTO(User user) {
        return new UserDTO(user);
    }
}
