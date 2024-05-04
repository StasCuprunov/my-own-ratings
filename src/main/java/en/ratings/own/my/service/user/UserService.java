package en.ratings.own.my.service.user;

import en.ratings.own.my.exception.RoleNotFoundException;
import en.ratings.own.my.exception.user.UserCreationFailedException;
import en.ratings.own.my.exception.user.UserNotFoundByEmail;
import en.ratings.own.my.model.User;
import en.ratings.own.my.model.role.Role;
import en.ratings.own.my.model.role.RoleAssignment;
import en.ratings.own.my.repository.UserRepository;
import en.ratings.own.my.repository.role.RoleAssignmentRepository;
import en.ratings.own.my.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static en.ratings.own.my.utility.EnumUtility.RoleUserAsString;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_EMAIL_ALREADY_EXISTS;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_EMAIL_SYNTAX;
import static en.ratings.own.my.service.user.UserValidation.isEmailSyntaxAllowed;
import static en.ratings.own.my.service.user.UserValidation.passwordValidation;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleAssignmentRepository roleAssignmentRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserService() {

    }

    public User findByEmail(String email) throws Exception {
        Optional<User> user = findUserByEmail(email);

        if (user.isEmpty()) {
            throw new UserNotFoundByEmail(email);
        }
        return user.get();
    }

    public User create(String email, String firstName, String surname, String password) throws Exception {
        ArrayList<String> keysForException = new ArrayList<>(emailValidation(email));
        keysForException.add(passwordValidation(password));

        if (!keysForException.isEmpty()) {
            throw new UserCreationFailedException(keysForException);
        }

        return createUserInDatabase(new User(email, firstName, surname, password));
    }

    private User createUserInDatabase(User user) throws Exception {
        User userResult = saveUser(user);
        String roleName = RoleUserAsString();
        Optional<Role> role = findRoleByName(roleName);

        if (role.isEmpty()) {
            throw new RoleNotFoundException(roleName);
        }

        Long roleId = role.get().getId();
        Long userId = userResult.getId();

        saveRoleAssignment(new RoleAssignment(userId, roleId));

        return userResult;
    }

    private Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private User saveUser(User user) {
        return userRepository.save(user);
    }

    private Optional<Role> findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    private RoleAssignment saveRoleAssignment(RoleAssignment roleAssignment) {
        return roleAssignmentRepository.save(roleAssignment);
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

    private boolean isEmailAvailable(String email)  {
        return findUserByEmail(email).isEmpty();
    }
}
