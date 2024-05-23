package en.ratings.own.my.test.integration.controller;

import en.ratings.own.my.model.role.Role;
import en.ratings.own.my.test.integration.AbstractIntegrationTest;
import en.ratings.own.my.model.Login;
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

import static en.ratings.own.my.test.constant.TestConstants.EXPECTED_ONE;
import static en.ratings.own.my.test.utility.CreateUserUtility.createUserLiangPai;
import static en.ratings.own.my.test.utility.GeneratorUtility.printExceptionMessage;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToUserCreationFailedException;
import static en.ratings.own.my.test.utility.asserts.AssertThatUtility.assertThatIdIsDefined;
import static en.ratings.own.my.test.utility.asserts.AssertThatUtility.assertThatIsNotNull;
import static en.ratings.own.my.test.utility.asserts.AssertThatStatusCodeUtility.assertThatStatusCodeIsCreated;
import static en.ratings.own.my.test.utility.CreateUserUtility.createUserStevenWorm;
import static en.ratings.own.my.test.utility.CreateUserUtility.createUserLiangPaiWithDefinedId;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserLiangPaiWithDifferentFirstName;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserLiangPaiWithDifferentPassword;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserLiangPaiWithDifferentSurname;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserLiangPaiWithInvalidEmail;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserLiangPaiWithInvalidSpecialCharacterPassword;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserLiangPaiWithTooLongPassword;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserLiangPaiWithTooShortPassword;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserLiangPaiWithoutDigitsPassword;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserLiangPaiWithoutLowerCaseLetterPassword;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserLiangPaiWithoutUpperCaseLetterPassword;
import static en.ratings.own.my.test.utility.CreateUserUtility.
        createUserLiangPaiWithoutValidSpecialCharacterPassword;
import static en.ratings.own.my.test.utility.HttpResponseUtility.createHttpServletResponse;
import static en.ratings.own.my.utility.EnumUtility.roleUserAsString;
import static en.ratings.own.my.utility.math.MathUtility.isLastIndex;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        testValidCreate(createUserLiangPai());
    }

    @Test
    public void testValidCreateWithValidInputWithLoggedIn() {
        loginWithStevenWorm();
        testValidCreate(createUserLiangPai());
    }

    @Test
    public void testInvalidCreateWithDefinedId() {
        testInvalidCreate(createUserLiangPaiWithDefinedId());
    }

    @Test
    public void testInvalidCreateWithNotAvailableEmailButWithDifferentFirstName() {
        testCreateWithNotAvailableEmailWithLiangPai(createUserLiangPaiWithDifferentFirstName());
    }

    @Test
    public void testInvalidCreateWithNotAvailableEmailButWithDifferentSurname() {
        testCreateWithNotAvailableEmailWithLiangPai(createUserLiangPaiWithDifferentSurname());
    }

    @Test
    public void testInvalidCreateWithNotAvailableEmailButWithDifferentPassword() {
        testCreateWithNotAvailableEmailWithLiangPai(createUserLiangPaiWithDifferentPassword());
    }

    @Test
    public void testInvalidCreateWithInvalidEmail() {
        testInvalidCreateWithoutStoredEntries(createUserLiangPaiWithInvalidEmail(), findAllRoleAssignmentRepository());
    }

    @Test
    public void testInvalidCreateWithTooShortPassword() {
        testInvalidCreateWithoutStoredEntries(createUserLiangPaiWithTooShortPassword(),
                findAllRoleAssignmentRepository());
    }

    @Test
    public void testInvalidCreateWithTooLongPassword() {
        testInvalidCreateWithoutStoredEntries(createUserLiangPaiWithTooLongPassword(),
                findAllRoleAssignmentRepository());
    }

    @Test
    public void testInvalidCreateWithoutDigitsInPassword() {
        testInvalidCreateWithoutStoredEntries(createUserLiangPaiWithoutDigitsPassword(),
                findAllRoleAssignmentRepository());
    }

    @Test
    public void testInvalidCreateWithoutLowerCaseLetterInPassword() {
        testInvalidCreateWithoutStoredEntries(createUserLiangPaiWithoutLowerCaseLetterPassword(),
                findAllRoleAssignmentRepository());
    }

    @Test
    public void testInvalidCreateWithoutUpperCaseLetterInPassword() {
        testInvalidCreateWithoutStoredEntries(createUserLiangPaiWithoutUpperCaseLetterPassword(),
                findAllRoleAssignmentRepository());
    }

    @Test
    public void testInvalidCreateWithoutValidSpecialCharacterInPassword() {
        testInvalidCreateWithoutStoredEntries(createUserLiangPaiWithoutValidSpecialCharacterPassword(),
                findAllRoleAssignmentRepository());
    }

    @Test
    public void testInvalidCreateWithInvalidSpecialCharacterInPassword() {
        testInvalidCreateWithoutStoredEntries(createUserLiangPaiWithInvalidSpecialCharacterPassword(),
                findAllRoleAssignmentRepository());
    }

    private void testValidCreate(User user) {
        ResponseEntity<UserDTO> tryResponseEntity = null;
        try {
            tryResponseEntity = userController.create(user);
        } catch (Exception e) {
            printExceptionMessage(e);
        }

        ResponseEntity<UserDTO> responseEntity = tryResponseEntity;
        assertAll("test valid create user",
                () -> checkResponseEntityAfterCreate(user, responseEntity),
                () -> compareUserWithStoredUserAfterCreate(user),
                () -> checkRoleAssignmentAfterCreate(user)
        );
    }

    private void checkRoleAssignmentAfterCreate(User user) {
        int index = 0;

        ArrayList<RoleAssignment> roleAssignments = findAllByUserIdRoleAssignmentRepository(user.getId());

        String roleId = roleAssignments.get(index).getRoleId();

        assertAll(
                () -> assertThat(roleAssignments.size()).isEqualTo(EXPECTED_ONE),
                () -> assertThat(findByIdRoleRepository(roleId).get().getName()).isEqualTo(roleUserAsString())
        );
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

    private void testInvalidCreateWithoutStoredEntries(User user, ArrayList<RoleAssignment> roleAssignmentsBefore) {
        assertAll(
                () -> testInvalidCreate(user),
                () -> assertThat(findByEmailUserRepository(user.getEmail())).isEmpty(),
                () -> compareRoleAssignmentTable(roleAssignmentsBefore)
        );
    }

    private void compareRoleAssignmentTable(ArrayList<RoleAssignment> listOfRoleAssignmentsBefore) {
        ArrayList<RoleAssignment> listOfStoredRoleAssignments = findAllRoleAssignmentRepository();
        int sizeOfStoredRoleAssignments = listOfStoredRoleAssignments.size();
        ArrayList<String> notFoundRoleAssignmentIdsBefore = new ArrayList<>();
        for (RoleAssignment roleAssignmentBefore: listOfRoleAssignmentsBefore) {
            for (int index = 0; index < sizeOfStoredRoleAssignments; index++) {
                if (listOfStoredRoleAssignments.get(index).equals(roleAssignmentBefore)) {
                    break;
                }
                else if (isLastIndex(index, sizeOfStoredRoleAssignments)) {
                    notFoundRoleAssignmentIdsBefore.add(roleAssignmentBefore.getId());
                }
            }
        }
        assertAll(
                () -> assertThat(sizeOfStoredRoleAssignments).isEqualTo(listOfRoleAssignmentsBefore.size()),
                () -> assertThat(notFoundRoleAssignmentIdsBefore.isEmpty()).isTrue()
        );
    }

    private void testCreateWithNotAvailableEmailWithLiangPai(User differentUserButWithLiangPaisEmail) {
        try {
            userController.create(createUserLiangPai());
        } catch (Exception e) {
            printExceptionMessage(e);
        }
        testInvalidCreate(differentUserButWithLiangPaisEmail);
    }

    private void checkResponseEntityAfterCreate(User user, ResponseEntity<UserDTO> responseEntity) {
        assertAll(
                () -> assertThatIsNotNull(responseEntity),
                () -> assertThatStatusCodeIsCreated(responseEntity),
                () -> compareUserWithUserDTOAfterCreate(user, responseEntity.getBody())
        );
    }

    private void compareUserWithUserDTOAfterCreate(User user, UserDTO userDTO) {
        assertAll(
                () -> assertThat(user.getId()).isEqualTo(userDTO.getId()),
                () -> assertThat(user.getEmail()).isEqualTo(userDTO.getEmail()),
                () -> assertThat(user.getFirstName()).isEqualTo(userDTO.getFirstName()),
                () -> assertThat(user.getSurname()).isEqualTo(userDTO.getSurname())
        );
    }

    private void compareUserWithStoredUserAfterCreate(User user) {
        Optional<User> userResult = findByEmailUserRepository(user.getEmail());
        User storedUser = userResult.get();

        assertAll(
                () -> assertThatIdIsDefined(storedUser.getId()),
                () -> assertThat(storedUser.getEmail()).isEqualTo(user.getEmail()),
                () -> assertThat(storedUser.getFirstName()).isEqualTo(user.getFirstName()),
                () -> assertThat(storedUser.getSurname()).isEqualTo(user.getSurname()),
                () -> assertThat(storedUser.getPassword()).isEqualTo(user.getPassword())
        );
    }

    private void loginWithStevenWorm() {
        User loggedInUser = createUserStevenWorm();
        String rawPassword = loggedInUser.getPassword();

        Login login = new Login(loggedInUser.getEmail(), rawPassword);
        try {
            authenticationController.login(login, createHttpServletResponse());
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
