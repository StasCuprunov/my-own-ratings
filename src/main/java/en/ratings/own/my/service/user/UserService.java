package en.ratings.own.my.service.user;

import en.ratings.own.my.exception.RoleNotFoundException;
import en.ratings.own.my.exception.user.creation.UserCreationFailedException;
import en.ratings.own.my.exception.user.UserNotFoundByEmailException;
import en.ratings.own.my.model.User;
import en.ratings.own.my.model.role.Role;
import en.ratings.own.my.model.role.RoleAssignment;
import en.ratings.own.my.service.repository.UserRepositoryService;
import en.ratings.own.my.service.repository.role.RoleAssignmentRepositoryService;
import en.ratings.own.my.service.repository.role.RoleRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static en.ratings.own.my.utility.EnumUtility.RoleUserAsString;
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

    @Autowired
    public UserService(UserRepositoryService userRepositoryService,
                       RoleAssignmentRepositoryService roleAssignmentRepositoryService,
                       RoleRepositoryService roleRepositoryService) {
        this.userRepositoryService = userRepositoryService;
        this.roleAssignmentRepositoryService = roleAssignmentRepositoryService;
        this.roleRepositoryService = roleRepositoryService;
    }

    public User findByEmail(String email) throws Exception {
        Optional<User> user = userRepositoryService.findByEmail(email);

        if (user.isEmpty()) {
            throw new UserNotFoundByEmailException(email);
        }
        return user.get();
    }

    public User create(String email, String firstName, String surname, String password) throws Exception {
        ArrayList<String> keysForException = emailValidation(email);
        keysForException = addExistentStringToArrayList(keysForException, passwordValidation(password));

        if (!keysForException.isEmpty()) {
            throw new UserCreationFailedException(keysForException);
        }
        return createUser(new User(email, firstName, surname, password));
    }

    private User createUser(User user) throws Exception {
        User userResult = userRepositoryService.save(user);
        String roleName = RoleUserAsString();
        Optional<Role> role = roleRepositoryService.findByName(roleName);

        if (role.isEmpty()) {
            throw new RoleNotFoundException(roleName);
        }

        Long roleId = role.get().getId();
        Long userId = userResult.getId();

        roleAssignmentRepositoryService.save(new RoleAssignment(userId, roleId));

        return userResult;
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
        return userRepositoryService.findByEmail(email).isEmpty();
    }
}
