package en.ratings.own.my.test.integration.controller;

import en.ratings.own.my.model.role.Role;
import en.ratings.own.my.test.integration.AbstractIntegrationTest;
import en.ratings.own.my.dto.LoginDTO;
import en.ratings.own.my.dto.UserDTO;
import en.ratings.own.my.model.User;
import en.ratings.own.my.model.role.RoleAssignment;
import en.ratings.own.my.repository.role.RoleAssignmentRepository;
import en.ratings.own.my.repository.role.RoleRepository;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static en.ratings.own.my.test.constant.TestConstants.EXPECTED_EXACT_ONE;
import static en.ratings.own.my.test.utility.GeneratorUtility.printExceptionMessage;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToUserCreationFailedException;
import static en.ratings.own.my.test.utility.asserts.AssertThatUtility.assertThatIdIsDefined;
import static en.ratings.own.my.test.utility.asserts.AssertThatUtility.assertThatIsNotNull;
import static en.ratings.own.my.test.utility.asserts.AssertThatStatusCodeUtility.assertThatStatusCodeIsCreated;
import static en.ratings.own.my.test.utility.CreateUserUtility.createUserFalakNoorahKhoury;
import static en.ratings.own.my.test.utility.CreateUserUtility.createUserStevenWorm;
import static en.ratings.own.my.test.utility.CreateUserUtility.createUserStevenWormWithDefinedId;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserStevenWormWithDifferentFirstName;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserStevenWormWithDifferentPassword;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserStevenWormWithDifferentSurname;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserStevenWormWithInvalidEmail;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserStevenWormWithInvalidSpecialCharacterPassword;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserStevenWormWithTooLongPassword;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserStevenWormWithTooShortPassword;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserStevenWormWithoutDigitsPassword;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserStevenWormWithoutLowerCaseLetterPassword;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserStevenWormWithoutUpperCaseLetterPassword;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserStevenWormWithoutValidSpecialCharacterPassword;
import static en.ratings.own.my.test.utility.HttpResponseUtility.createHttpServletResponse;
import static en.ratings.own.my.utility.EnumUtility.roleUserAsString;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private RoleAssignmentRepository roleAssignmentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @After
    public void clean() {
        deleteAllUserRepository();
        roleAssignmentRepository.deleteAll();
    }

    @Test
    public void testValidCreateWithValidInputWithoutLoggedIn() {
        testValidCreate(createUserStevenWorm());
    }

    @Test
    public void testValidCreateWithValidInputWithLoggedIn() {
        login();
        testValidCreate(createUserFalakNoorahKhoury());
    }

    @Test
    public void testInvalidCreateWithDefinedId() {
        testInvalidCreate(createUserStevenWormWithDefinedId());
    }

    @Test
    public void testInvalidCreateWithNotAvailableEmailButWithDifferentFirstName() {
        testCreateWithNotAvailableEmail(createUserStevenWormWithDifferentFirstName());
    }

    @Test
    public void testInvalidCreateWithNotAvailableEmailButWithDifferentSurname() {
        testCreateWithNotAvailableEmail(createUserStevenWormWithDifferentSurname());
    }

    @Test
    public void testInvalidCreateWithNotAvailableEmailButWithDifferentPassword() {
        testCreateWithNotAvailableEmail(createUserStevenWormWithDifferentPassword());
    }

    @Test
    public void testInvalidCreateWithInvalidEmail() {
        testInvalidCreateWithoutStoredEntries(createUserStevenWormWithInvalidEmail());
    }

    @Test
    public void testInvalidCreateWithTooShortPassword() {
        testInvalidCreateWithoutStoredEntries(createUserStevenWormWithTooShortPassword());
    }

    @Test
    public void testInvalidCreateWithTooLongPassword() {
        testInvalidCreateWithoutStoredEntries(createUserStevenWormWithTooLongPassword());
    }

    @Test
    public void testInvalidCreateWithoutDigitsInPassword() {
        testInvalidCreateWithoutStoredEntries(createUserStevenWormWithoutDigitsPassword());
    }

    @Test
    public void testInvalidCreateWithoutLowerCaseLetterInPassword() {
        testInvalidCreateWithoutStoredEntries(createUserStevenWormWithoutLowerCaseLetterPassword());
    }

    @Test
    public void testInvalidCreateWithoutUpperCaseLetterInPassword() {
        testInvalidCreateWithoutStoredEntries(createUserStevenWormWithoutUpperCaseLetterPassword());
    }

    @Test
    public void testInvalidCreateWithoutValidSpecialCharacterInPassword() {
        testInvalidCreateWithoutStoredEntries(createUserStevenWormWithoutValidSpecialCharacterPassword());
    }

    @Test
    public void testInvalidCreateWithInvalidSpecialCharacterInPassword() {
        testInvalidCreateWithoutStoredEntries(createUserStevenWormWithInvalidSpecialCharacterPassword());
    }

    private void testValidCreate(User user) {
        ResponseEntity<UserDTO> responseEntity = null;
        try {
            responseEntity = userController.create(user);
        } catch (Exception e) {
            printExceptionMessage(e);
        }
        checkResponseEntityAfterCreate(user, responseEntity);
        compareUserWithStoredUserAfterCreate(user);
        checkRoleAssignmentAfterCreate(user);
    }

    private void checkRoleAssignmentAfterCreate(User user) {
        int index = 0;

        ArrayList<RoleAssignment> roleAssignments = findAllByUserIdRoleAssignmentRepository(user.getId());
        assertThat(roleAssignments.size()).isEqualTo(EXPECTED_EXACT_ONE);

        String roleId = roleAssignments.get(index).getRoleId();

        assertThat(findByIdRoleRepository(roleId).get().getName()).isEqualTo(roleUserAsString());
    }

    private void testInvalidCreate(User user) {
        Exception foundException = new Exception();
        try {
            userController.create(user);
        } catch (Exception e) {
            foundException = e;
        }
        assertThatExceptionIsEqualToUserCreationFailedException(foundException);
    }

    private void testInvalidCreateWithoutStoredEntries(User user) {
        testInvalidCreate(user);
        assertThat(findByEmailUserRepository(user.getEmail())).isEmpty();
        assertThat(findAllRoleAssignmentRepository().isEmpty()).isTrue();
    }

    private void testCreateWithNotAvailableEmail(User differentUserButWithStevenWormsEmail) {
        try {
            userController.create(createUserStevenWorm());
        } catch (Exception e) {
            printExceptionMessage(e);
        }
        testInvalidCreate(differentUserButWithStevenWormsEmail);
    }

    private void checkResponseEntityAfterCreate(User user, ResponseEntity<UserDTO> responseEntity) {
        assertThatIsNotNull(responseEntity);
        assertThatStatusCodeIsCreated(responseEntity);
        compareUserWithUserDTOAfterCreate(user, responseEntity.getBody());
    }

    private void compareUserWithUserDTOAfterCreate(User user, UserDTO userDTO) {
        assertThat(user.getId()).isEqualTo(userDTO.getId());
        assertThat(user.getEmail()).isEqualTo(userDTO.getEmail());
        assertThat(user.getFirstName()).isEqualTo(userDTO.getFirstName());
        assertThat(user.getSurname()).isEqualTo(userDTO.getSurname());
    }

    private void compareUserWithStoredUserAfterCreate(User user) {
        Optional<User> userResult = findByEmailUserRepository(user.getEmail());

        assertThat(userResult).isPresent();
        User storedUser = userResult.get();

        assertThatIdIsDefined(storedUser.getId());
        assertThat(storedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(storedUser.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(storedUser.getSurname()).isEqualTo(user.getSurname());
        assertThat(storedUser.getPassword()).isEqualTo(user.getPassword());
    }

    private void login() {
        User loggedInUser = createUserStevenWorm();
        String rawPassword = loggedInUser.getPassword();
        try {
            userController.create(loggedInUser);
        } catch (Exception e) {
            printExceptionMessage(e);
        }

        LoginDTO loginDTO = new LoginDTO(loggedInUser.getEmail(), rawPassword);
        try {
            authenticationController.login(loginDTO, createHttpServletResponse());
        } catch (Exception e) {
            printExceptionMessage(e);
        }
    }

    private ArrayList<RoleAssignment> findAllByUserIdRoleAssignmentRepository(String userId) {
        return roleAssignmentRepository.findAllByUserId(userId);
    }

    private ArrayList<RoleAssignment> findAllRoleAssignmentRepository() {
        return roleAssignmentRepository.findAll();
    }

    private Optional<Role> findByIdRoleRepository(String id) {
        return roleRepository.findById(id);
    }

    private Optional<User> findByEmailUserRepository(String email) {
        return userRepository.findByEmail(email);
    }
}
