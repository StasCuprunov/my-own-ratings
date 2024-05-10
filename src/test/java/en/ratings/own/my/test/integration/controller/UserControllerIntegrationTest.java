package en.ratings.own.my.test.integration.controller;

import en.ratings.own.my.AbstractIntegrationTest;
import en.ratings.own.my.controller.AuthenticationController;
import en.ratings.own.my.controller.UserController;
import en.ratings.own.my.dto.LoginDTO;
import en.ratings.own.my.dto.UserDTO;
import en.ratings.own.my.model.User;
import en.ratings.own.my.repository.UserRepository;
import en.ratings.own.my.service.repository.role.RoleAssignmentRepositoryService;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static en.ratings.own.my.AssertThatUtility.assertThatExceptionIsEqualToUserCreationFailedException;
import static en.ratings.own.my.AssertThatUtility.assertThatId;
import static en.ratings.own.my.AssertThatUtility.assertThatIsNotNull;
import static en.ratings.own.my.AssertThatUtility.assertThatStatusCodeIsCreated;
import static en.ratings.own.my.test.integration.controller.utility.CreateUserUtility.createUserFalakNoorahKhoury;
import static en.ratings.own.my.test.integration.controller.utility.CreateUserUtility.createUserStevenWorm;
import static en.ratings.own.my.test.integration.controller.utility.CreateUserUtility.
        createUserStevenWormWithDifferentFirstName;
import static en.ratings.own.my.test.integration.controller.utility.HttpResponseUtility.createHttpServletResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserController userController;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleAssignmentRepositoryService roleAssignmentRepositoryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @After
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testCreateWithValidInputWithoutLoggedInResult() {
        User user = createUserStevenWorm();
        ResponseEntity<UserDTO> responseEntity = null;
        try {
            responseEntity = userController.create(user);
        } catch (Exception ignored) {

        }
        assertThatResultAfterCreate(user, responseEntity);
        assertThatDatabaseEntryAfterCreate(user);
    }

    @Test
    public void testCreateWithValidInputWithLoggedIn() throws Exception {
        User loggedInUser = createUserStevenWorm();
        String rawPassword = loggedInUser.getPassword();
        ResponseEntity<UserDTO> responseEntity = null;
        try {
            responseEntity = userController.create(loggedInUser);
        } catch (Exception ignored) {

        }
        LoginDTO loginDTO = new LoginDTO(loggedInUser.getEmail(), rawPassword);
        authenticationController.login(loginDTO, createHttpServletResponse());

        User newUser = createUserFalakNoorahKhoury();
        try {
            responseEntity = userController.create(newUser);
        } catch (Exception ignored) {

        }
        assertThatResultAfterCreate(newUser, responseEntity);
        assertThatDatabaseEntryAfterCreate(newUser);
    }

    @Test
    public void testCreateWithNotAvailableEmail() {
        Exception expectedException = new Exception();
        try {
            userController.create(createUserStevenWorm());
        } catch (Exception ignored) {

        }
        try {
            userController.create(createUserStevenWormWithDifferentFirstName());
        }
        catch (Exception e) {
            expectedException = e;
        }
        assertThatExceptionIsEqualToUserCreationFailedException(expectedException);
    }

    private void assertThatResultAfterCreate(User user, ResponseEntity<UserDTO> responseEntity) {
        assertThatIsNotNull(responseEntity);
        assertThatStatusCodeIsCreated(responseEntity);
        assertThatUserResultIsEqualAfterCreate(user, responseEntity.getBody());
    }

    private void assertThatDatabaseEntryAfterCreate(User user) {
        Optional<User> userResult = userRepository.findByEmail(user.getEmail());

        assertThat(userResult).isPresent();
        User storedUser = userResult.get();

        assertThatId(storedUser.getId());
        assertThat(storedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(storedUser.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(storedUser.getSurname()).isEqualTo(user.getSurname());
        assertThat(storedUser.getPassword()).isEqualTo(user.getPassword());
    }

    private void assertThatUserResultIsEqualAfterCreate(User user, UserDTO userDTO) {
        assertThatUserIsEqual(user, userDTO, true);
    }

    private void assertThatUserIsEqual(User user, UserDTO userDTO, boolean afterUpdate) {
        if (afterUpdate) {
            assertThat(user.getId()).isEqualTo(userDTO.getId());
        }
        assertThat(user.getEmail()).isEqualTo(userDTO.getEmail());
        assertThat(user.getFirstName()).isEqualTo(userDTO.getFirstName());
        assertThat(user.getSurname()).isEqualTo(userDTO.getSurname());
    }
}
