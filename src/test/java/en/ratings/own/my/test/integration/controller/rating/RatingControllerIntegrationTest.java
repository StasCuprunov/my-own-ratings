package en.ratings.own.my.test.integration.controller.rating;

import en.ratings.own.my.AbstractIntegrationTest;
import en.ratings.own.my.controller.AuthenticationController;
import en.ratings.own.my.controller.UserController;
import en.ratings.own.my.controller.rating.RatingController;
import en.ratings.own.my.dto.LoginDTO;
import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.User;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.model.rating.RatingEntry;
import en.ratings.own.my.repository.UserRepository;
import en.ratings.own.my.repository.rating.RangeOfValuesRepository;
import en.ratings.own.my.repository.rating.RatingEntryRepository;
import en.ratings.own.my.repository.rating.RatingRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static en.ratings.own.my.test.integration.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingByIdNotAllowedException;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingByIdNotFoundException;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingCreationFailedException;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingDeleteByIdNotAllowedException;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatUtility.assertThatId;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatUtility.assertThatStatusCodeIsCreated;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatUtility.assertThatStatusCodeIsNoContent;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatUtility.assertThatStatusCodeIsOk;
import static en.ratings.own.my.test.integration.utility.CreateUserUtility.createUserFalakNoorahKhoury;
import static en.ratings.own.my.test.integration.utility.CreateUserUtility.createUserStevenWorm;
import static en.ratings.own.my.test.integration.utility.HttpResponseUtility.createHttpServletResponse;
import static en.ratings.own.my.test.integration.utility.rating.CreateRatingDTOUtility.
        INVALID_RATING_DTO_BECAUSE_EMPTY_NAME;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.
        INVALID_RATING_DTO_BOOKS_WITH_NEGATIVE_STEP_WIDTH;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.
        INVALID_RATING_DTO_BOOKS_WITH_ZERO_STEP_WIDTH;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.
        createValidRatingDTOBooksWithGermanGradingAndDefinedRatingEntries;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        INVALID_RATING_DTO_DRINKS_WITH_MINIMUM_EQUALS_TO_MAXIMUM;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        INVALID_RATING_DTO_DRINKS_WITH_MINIMUM_GREATER_THAN_MAXIMUM;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        INVALID_RATING_DTO_DRINKS_WITH_UNAVAILABLE_MAXIMUM;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        INVALID_RATING_DTO_WITH_EMPTY_NAME_AND_NO_DESCRIPTION_AND_WITH_GERMAN_GRADING;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        VALID_RATING_DTO_DRINKS_WITH_NO_DESCRIPTION_AND_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        createValidRatingEntryAppleJuiceForDrinksWithNegativeMinimum;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        createValidRatingEntryCokeForDrinksWithNegativeMinimum;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        createValidRatingEntryRedBullForDrinksWithNegativeMinimum;
import static en.ratings.own.my.utility.Utility.isLastIndex;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.security.core.context.SecurityContextHolder.clearContext;

public class RatingControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private RatingController ratingController;

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RangeOfValuesRepository rangeOfValuesRepository;

    @Autowired
    private RatingEntryRepository ratingEntryRepository;

    private User userStevenWorm;

    private User userFalakNoorahKhoury;

    private final int MAX_RANGE_OF_VALUES_WITH_SAME_ATTRIBUTES_IN_DOCUMENT = 1;

    private final int NUMBER_OF_RATING_ENTRIES_AFTER_CREATE_RATING = 0;

    private final int NUMBER_OF_UNIQUE_RATING_ENTRIES = 1;

    @Before
    public void setup() {
        userStevenWorm = storeUserInDatabase(createUserStevenWorm());
        userFalakNoorahKhoury = storeUserInDatabase(createUserFalakNoorahKhoury());
    }

    @After
    public void clean() {
        userRepository.deleteAll();
        ratingRepository.deleteAll();
        rangeOfValuesRepository.deleteAll();
        ratingEntryRepository.deleteAll();
    }

    @Test
    public void testValidCreate() {
        testValidCreate(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING);
    }

    @Test
    public void testValidCreateWithDefinedId() {
        RatingDTO input = VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING;
        input.setId("test");
        testValidCreate(userFalakNoorahKhoury, input);
    }

    @Test
    public void testValidCreateWithDefinedRatingEntries() {
        testValidCreate(userStevenWorm, createValidRatingDTOBooksWithGermanGradingAndDefinedRatingEntries());
    }

    @Test
    public void testValidCreateWithSameInputsButDifferentUsers() {
        RatingDTO input = VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING;
        testValidCreate(userStevenWorm, input);
        testValidCreate(userFalakNoorahKhoury, input);
    }

    @Test
    public void testValidCreateWithSameRatingNamesButDifferentUsers() {
        testValidCreate(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING);
        testValidCreate(userFalakNoorahKhoury, VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING);
    }

    @Test
    public void testValidCreateWithNegativeMinimum() {
        testValidCreate(userFalakNoorahKhoury, VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM);
    }

    @Test
    public void testValidCreateWithNoDescriptionAndWithNegativeMinimumAndMaximum() {
        testValidCreate(userStevenWorm,
                VALID_RATING_DTO_DRINKS_WITH_NO_DESCRIPTION_AND_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM);
    }

    @Test
    public void testInvalidCreateWithoutLoggedIn() {
        logout();
        RatingDTO input = VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING;
        input.setUserId(userStevenWorm.getId());
        Exception foundException  = new Exception();
        try {
            ratingController.create(input);
        } catch (Exception e) {
            foundException = e;
        }
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException(foundException);
    }

    @Test
    public void testInvalidCreateWithEmptyName() {
        testInvalidCreate(userStevenWorm, INVALID_RATING_DTO_BECAUSE_EMPTY_NAME);
    }

    @Test
    public void testInvalidCreateWithNegativeStepWidth() {
        testInvalidCreate(userFalakNoorahKhoury, INVALID_RATING_DTO_BOOKS_WITH_NEGATIVE_STEP_WIDTH);
    }

    @Test
    public void testInvalidCreateWithZeroStepWidth() {
        testInvalidCreate(userFalakNoorahKhoury, INVALID_RATING_DTO_BOOKS_WITH_ZERO_STEP_WIDTH);
    }

    @Test
    public void testInvalidCreateWithEmptyNameAndNoDescription() {
        testInvalidCreate(userStevenWorm, INVALID_RATING_DTO_WITH_EMPTY_NAME_AND_NO_DESCRIPTION_AND_WITH_GERMAN_GRADING);
    }

    @Test
    public void testInvalidCreateWithMinimumEqualsToMaximum() {
        testInvalidCreate(userStevenWorm, INVALID_RATING_DTO_DRINKS_WITH_MINIMUM_EQUALS_TO_MAXIMUM);
    }

    @Test
    public void testInvalidCreateWithMinimumGreaterThanMaximum() {
        testInvalidCreate(userFalakNoorahKhoury, INVALID_RATING_DTO_DRINKS_WITH_MINIMUM_GREATER_THAN_MAXIMUM);
    }

    @Test
    public void testInvalidCreateWithUnavailableMaximum() {
        testInvalidCreate(userStevenWorm, INVALID_RATING_DTO_DRINKS_WITH_UNAVAILABLE_MAXIMUM);
    }

    @Test
    public void testInvalidCreateWithForeignUserId() {
        testInvalidCreate(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING, userFalakNoorahKhoury.getId());
    }

    @Test
    public void testValidFindByIdWithDrinks() {
        ResponseEntity<RatingDTO> responseEntity = createValidRating(userStevenWorm,
                VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM);
        String ratingId = responseEntity.getBody().getId();

        createRatingEntriesForDrinksWithNegativeMinimum(ratingId);

        testValidFindById(responseEntity.getBody());
    }

    @Test
    public void testInvalidFindByIdWithoutLoggedIn() {
        ResponseEntity<RatingDTO> responseEntity = createValidRating(userStevenWorm,
                VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING);
        logout();
        Exception foundException = new Exception();
        try {
            ratingController.findById(responseEntity.getBody().getId());
        } catch (Exception e) {
            foundException = e;
        }
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException(foundException);
    }

    @Test
    public void testInvalidFindByIdWithDifferentUser() {
        ResponseEntity<RatingDTO> responseEntity = createValidRating(userStevenWorm,
                VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING);
        login(userFalakNoorahKhoury);
        Exception foundException = new Exception();
        try {
            ratingController.findById(responseEntity.getBody().getId());
        } catch (Exception e) {
            foundException = e;
        }
        assertThatExceptionIsEqualToRatingByIdNotAllowedException(foundException);
    }

    @Test
    public void testInvalidFindByIdWithNotExistentId() {
        ResponseEntity<RatingDTO> responseEntity = createValidRating(userStevenWorm,
                VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING);
        Exception foundException = new Exception();
        try {
            ratingController.findById(responseEntity.getBody().getId() + "test");
        } catch (Exception e) {
            foundException = e;
        }
        assertThatExceptionIsEqualToRatingByIdNotFoundException(foundException);
    }

    @Test
    public void testValidDeleteByIdWithDrinksAndDeletedRangeOfValues() {
        ResponseEntity<RatingDTO> responseEntity = createValidRating(userStevenWorm,
                VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM);
        String ratingId = responseEntity.getBody().getId();
        RangeOfValues rangeOfValues = responseEntity.getBody().getRangeOfValues();

        createRatingEntriesForDrinksWithNegativeMinimum(ratingId);

        testValidDeleteByIdAndDeletedRangeOfValues(ratingId, rangeOfValues);
    }

    @Test
    public void testValidDeleteByIdWithDrinksAndNotDeletedRangeOfValues() {
        createValidRating(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM);
        ResponseEntity<RatingDTO> responseEntity = createValidRating(userStevenWorm,
                VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM);
        String ratingId = responseEntity.getBody().getId();
        RangeOfValues rangeOfValues = responseEntity.getBody().getRangeOfValues();

        ArrayList<Rating> listOfRatingsBeforeDelete = ratingRepository.findAll();
        ArrayList<RangeOfValues> listOfRangeOfValuesBeforeDelete = rangeOfValuesRepository.findAll();
        ArrayList<RatingEntry> listOfRatingEntriesBeforeDelete = ratingEntryRepository.findAll();

        createRatingEntriesForDrinksWithNegativeMinimum(ratingId);

        testValidDeleteByIdAndNotDeletedRangeOfValues(ratingId, rangeOfValues);

        assertAll(
                () -> assertThat(isRatingMissingThatShouldExistAfterDeleteById(ratingId,
                        listOfRatingsBeforeDelete)).isFalse(),
                () -> assertThat(isRangeOfValuesThatShouldExistAfterDeleteById(null,
                        listOfRangeOfValuesBeforeDelete)).isFalse(),
                () -> assertThat(isRatingEntryMissingThatShouldExistAfterDeleteById(ratingId,
                        listOfRatingEntriesBeforeDelete)).isFalse()
        );
    }

    @Test
    public void testInvalidDeleteByIdWithWrongId() {
        ResponseEntity<RatingDTO> responseEntityCreate = createValidRating(userStevenWorm,
                VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM);
        String ratingId = responseEntityCreate.getBody().getId();
        createRatingEntriesForDrinksWithNegativeMinimum(ratingId);

        ArrayList<Rating> listOfRatingsBeforeDelete = ratingRepository.findAll();
        ArrayList<RangeOfValues> listOfRangeOfValuesBeforeDelete = rangeOfValuesRepository.findAll();
        ArrayList<RatingEntry> listOfRatingEntriesBeforeDelete = ratingEntryRepository.findAll();

        String deleteRatingId = ratingId + "test";
        Exception foundException = deleteByIdInvalid(deleteRatingId);

        assertAll(
                () -> assertThatExceptionIsEqualToRatingByIdNotFoundException(foundException),
                () -> assertThat(isRatingMissingThatShouldExistAfterDeleteById(deleteRatingId,
                        listOfRatingsBeforeDelete)).isFalse(),
                () -> assertThat(isRangeOfValuesThatShouldExistAfterDeleteById(null,
                        listOfRangeOfValuesBeforeDelete)).isFalse(),
                () -> assertThat(isRatingEntryMissingThatShouldExistAfterDeleteById(deleteRatingId,
                        listOfRatingEntriesBeforeDelete)).isFalse()
        );
    }

    @Test
    public void testInvalidDeleteByIdWithoutLoggedIn() {
        ResponseEntity<RatingDTO> responseEntity = createValidRating(userStevenWorm,
                VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM);
        logout();
        Exception foundException = deleteByIdInvalid(responseEntity.getBody().getId());
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException(foundException);
    }

    @Test
    public void testInvalidDeleteByIdWithDifferentUser() {
        ResponseEntity<RatingDTO> responseEntity = createValidRating(userStevenWorm,
                VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM);
        login(userFalakNoorahKhoury);
        Exception foundException = deleteByIdInvalid(responseEntity.getBody().getId());
        assertThatExceptionIsEqualToRatingDeleteByIdNotAllowedException(foundException);
    }

    private void testValidCreate(User user, RatingDTO input) {
        testValidCreate(user, input, null);
    }

    private void testValidCreate(User user, RatingDTO input, String differentUserId) {
        ResponseEntity<RatingDTO> responseEntity = createValidRating(user, input, differentUserId);
        checkValidCreate(input, responseEntity);
    }

    private ResponseEntity<RatingDTO> createValidRating(User user, RatingDTO input) {
        return createValidRating(user, input, null);
    }

    private ResponseEntity<RatingDTO> createValidRating(User user, RatingDTO input, String differentUserId) {
        login(user);
        String id = (differentUserId == null) ? user.getId() : differentUserId;
        input.setUserId(id);
        ResponseEntity<RatingDTO> responseEntity = null;
        try {
            responseEntity = ratingController.create(input);
        } catch (Exception ignored) {

        }
        return responseEntity;
    }

    private void testInvalidCreate(User user, RatingDTO input) {
        testInvalidCreate(user, input, null);
    }

    private void testInvalidCreate(User user, RatingDTO input, String differentUserId) {
        login(user);
        String id = (differentUserId == null) ? user.getId() : differentUserId;
        input.setUserId(id);
        Exception foundException  = new Exception();
        try {
            ratingController.create(input);
        } catch (Exception e) {
            foundException = e;
        }
        assertThatExceptionIsEqualToRatingCreationFailedException(foundException);
    }

    private void checkValidCreate(RatingDTO input, ResponseEntity<RatingDTO> responseEntity) {
        RatingDTO result = responseEntity.getBody();
        assertAll("Test valid create rating",
                () -> assertThatStatusCodeIsCreated(responseEntity),
                () -> compareInputWithResultAfterValidCreate(input, result),
                () -> compareResultWithDatabaseAfterValidCreate(result)
        );
    }

    private void compareInputWithResultAfterValidCreate(RatingDTO input, RatingDTO result) {
        assertThatId(result.getUserId());
        assertThat(input.equalsAfterCreate(result)).isTrue();
    }

    private void compareResultWithDatabaseAfterValidCreate(RatingDTO input) {
        Rating rating = compareInputWithRatingDocumentAfterCreate(input);
        compareInputWithRangeOfValuesDocumentAfterCreate(input, rating);
        compareInputRatingEntryDocumentAfterCreate(input);
    }

    private Rating compareInputWithRatingDocumentAfterCreate(RatingDTO input) {
        Rating rating = ratingRepository.findById(input.getId()).get();

        assertThat(input.getUserId()).isEqualTo(rating.getUserId());
        assertThat(input.getName()).isEqualTo(rating.getName());
        assertThat(input.getDescription()).isEqualTo(rating.getDescription());

        return rating;
    }

    private void compareInputWithRangeOfValuesDocumentAfterCreate(RatingDTO input, Rating foundRatingInDatabase) {
        RangeOfValues rangeOfValues = rangeOfValuesRepository.
                findById(foundRatingInDatabase.getRangeOfValuesId()).get();

        assertThat(input.getRangeOfValues().equalsWithoutId(rangeOfValues)).isTrue();
        compareIfRangeOfValuesExistsExactOnce(input);
    }

    private void compareIfRangeOfValuesExistsExactOnce(RatingDTO input) {
        RangeOfValues inputRangeOfValues = input.getRangeOfValues();
        ArrayList<RangeOfValues> listOfRangeOfValues =  rangeOfValuesRepository.findAll();

        int listSize = listOfRangeOfValues.size();
        int numberOfFoundRangeOfValues = 0;
        for (int index = 0; index < listSize; index++) {
            if (listOfRangeOfValues.get(index).equalsWithoutId(inputRangeOfValues)) {
                numberOfFoundRangeOfValues++;
            }
        }
        assertThat(numberOfFoundRangeOfValues).isEqualTo(MAX_RANGE_OF_VALUES_WITH_SAME_ATTRIBUTES_IN_DOCUMENT);
    }

    private void compareInputRatingEntryDocumentAfterCreate(RatingDTO input) {
        ArrayList<RatingEntry> ratingEntries = ratingEntryRepository.findAllByRatingId(input.getId());
        assertThat(ratingEntries.size()).isEqualTo(NUMBER_OF_RATING_ENTRIES_AFTER_CREATE_RATING);
    }


    private void testValidFindById(RatingDTO input) {
        ResponseEntity<RatingDTO> responseEntity = null;
        try {
            responseEntity = ratingController.findById(input.getId());
        } catch (Exception ignored) {

        }
        ResponseEntity<RatingDTO> resultResponseEntity = responseEntity;
        assertAll("Test valid findById rating",
                () -> assertThatStatusCodeIsOk(resultResponseEntity),
                () -> compareWithFoundById(input, resultResponseEntity.getBody())
        );
    }

    private void compareWithFoundById(RatingDTO input, RatingDTO foundRatingDTO) {
        assertAll(
                () -> assertThat(foundRatingDTO.getId()).isEqualTo(input.getId()),
                () -> assertThat(foundRatingDTO.getUserId()).isEqualTo(input.getUserId()),
                () -> assertThat(foundRatingDTO.getName()).isEqualTo(input.getName()),
                () -> assertThat(foundRatingDTO.getDescription()).isEqualTo(input.getDescription()),
                () -> assertThat(foundRatingDTO.getRangeOfValues().equals(input.getRangeOfValues())).isTrue()
        );
        compareFoundRatingEntries(foundRatingDTO);
    }

    private void compareFoundRatingEntries(RatingDTO foundRatingDTO) {
        for (RatingEntry foundRatingEntry: foundRatingDTO.getRatingEntries()) {
            int numberOfEqualRatingEntry = 0;
            for (RatingEntry storedRatingEntry: ratingEntryRepository.findAllByRatingId(foundRatingDTO.getId())) {
                if (storedRatingEntry.equals(foundRatingEntry)) {
                    numberOfEqualRatingEntry++;
                }
            }
            assertThat(numberOfEqualRatingEntry).isEqualTo(NUMBER_OF_UNIQUE_RATING_ENTRIES);
        }
    }

    private void checkValidDeleteById(String ratingId, ResponseEntity<Object> responseEntity) {
        assertAll("Test valid deleteById:",
                () -> assertThatStatusCodeIsNoContent(responseEntity),
                () -> assertThat(ratingRepository.findById(ratingId).isEmpty()).isTrue(),
                () -> assertThat(ratingEntryRepository.findAllByRatingId(ratingId).isEmpty()).isTrue()
        );
    }

    private void testValidDeleteByIdAndDeletedRangeOfValues(String ratingId, RangeOfValues rangeOfValues) {
        testValidDeleteById(ratingId, rangeOfValues, true);
    }

    private void testValidDeleteByIdAndNotDeletedRangeOfValues(String ratingId, RangeOfValues rangeOfValues) {
        testValidDeleteById(ratingId, rangeOfValues, false);
    }

    private void testValidDeleteById(String ratingId, RangeOfValues rangeOfValues, boolean shouldRangeOfValuesDeleted) {
        ResponseEntity<Object> responseEntity = deleteByIdSuccessful(ratingId);

        checkValidDeleteById(ratingId, responseEntity);
        checkRangeOfValuesAfterDeleteById(rangeOfValues, shouldRangeOfValuesDeleted);
    }

    private ResponseEntity<Object> deleteByIdSuccessful(String id) {
        try {
            return ratingController.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Exception deleteByIdInvalid(String id) {
        try {
            ratingController.deleteById(id);
        } catch (Exception e) {
            return e;
        }
        return new Exception();
    }

    private void checkRangeOfValuesAfterDeleteById(RangeOfValues rangeOfValues, boolean shouldRangeOfValuesDeleted) {
        boolean notFoundById = rangeOfValuesRepository.findById(rangeOfValues.getId()).isEmpty();
        boolean notFoundByAttributes = rangeOfValuesRepository.findByMinimumAndMaximumAndStepWidth(
                rangeOfValues.getMinimum(), rangeOfValues.getMaximum(), rangeOfValues.getStepWidth()).isEmpty();
        if (shouldRangeOfValuesDeleted) {
            assertAll("Range of values should be deleted:",
                    () -> assertThat(notFoundById).isTrue(),
                    () -> assertThat(notFoundByAttributes).isTrue()
            );
        }
        else {
            assertAll("Range of values should exist:",
                    () -> assertThat(notFoundById).isFalse(),
                    () -> assertThat(notFoundByAttributes).isFalse()
            );
        }
    }

    private void createRatingEntriesForDrinksWithNegativeMinimum(String ratingId) {
        ratingEntryRepository.save(createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId));
        ratingEntryRepository.save(createValidRatingEntryAppleJuiceForDrinksWithNegativeMinimum(ratingId));
        ratingEntryRepository.save(createValidRatingEntryRedBullForDrinksWithNegativeMinimum(ratingId));
    }

    private boolean isRatingMissingThatShouldExistAfterDeleteById(String deleteRatingId,
                                                                  ArrayList<Rating> listOfRatingsBeforeDelete) {
        ArrayList<Rating> listOfRatingsAfterDelete = ratingRepository.findAll();
        int sizeListOfRatingsAfterDelete = listOfRatingsAfterDelete.size();
        for (Rating ratingBeforeDelete: listOfRatingsBeforeDelete) {
            if (deleteRatingId.equals(ratingBeforeDelete.getId())) {
                continue;
            }
            for (int index = 0; index < sizeListOfRatingsAfterDelete; index++) {
                Rating ratingAfterDelete = listOfRatingsAfterDelete.get(index);
                if (ratingBeforeDelete.equals(ratingAfterDelete)) {
                    break;
                }
                else if (isLastIndex(index, sizeListOfRatingsAfterDelete)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isRangeOfValuesThatShouldExistAfterDeleteById(RangeOfValues deletedRangeOfValues,
                                                  ArrayList<RangeOfValues> listOfRangeOfValuesBeforeDelete) {
        ArrayList<RangeOfValues> listOfRangeOfValuesAfterDelete = rangeOfValuesRepository.findAll();
        int sizeListOfRangeOfValuesAfterDelete = listOfRangeOfValuesAfterDelete.size();
        boolean isDeletedRangeOfValuesNotNull = deletedRangeOfValues != null;
        for (RangeOfValues rangeOfValuesBeforeDelete: listOfRangeOfValuesBeforeDelete) {
            if (isDeletedRangeOfValuesNotNull && rangeOfValuesBeforeDelete.equals(deletedRangeOfValues)) {
                continue;
            }
            for (int index = 0; index < sizeListOfRangeOfValuesAfterDelete; index++) {
                RangeOfValues rangeOfValuesAfterDelete = listOfRangeOfValuesAfterDelete.get(index);
                if (rangeOfValuesBeforeDelete.equals(rangeOfValuesAfterDelete)) {
                    break;
                }
                else if (isLastIndex(index, sizeListOfRangeOfValuesAfterDelete)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isRatingEntryMissingThatShouldExistAfterDeleteById(String deleteRatingId,
                                                       ArrayList<RatingEntry> listOfRatingEntriesBeforeDelete) {
        ArrayList<RatingEntry> listOfRatingEntriesAfterDelete = ratingEntryRepository.findAll();
        int sizeListOfRatingsAfterDelete = listOfRatingEntriesAfterDelete.size();
        for (RatingEntry ratingEntryBeforeDelete: listOfRatingEntriesBeforeDelete) {
            if (deleteRatingId.equals(ratingEntryBeforeDelete.getRatingId())) {
                continue;
            }
            for (int index = 0; index < sizeListOfRatingsAfterDelete; index++) {
                RatingEntry ratingAfterDelete = listOfRatingEntriesAfterDelete.get(index);
                if (ratingEntryBeforeDelete.equals(ratingAfterDelete)) {
                    break;
                }
                else if (isLastIndex(index, sizeListOfRatingsAfterDelete)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void login(User user) {
        try {
            authenticationController.login(new LoginDTO(user.getEmail(), user.getPassword()),
                    createHttpServletResponse());
        } catch (Exception e) {

        }
    }

    private void logout() {
        clearContext();
    }

    private User storeUserInDatabase(User user) {
        String rawPassword = user.getPassword();
        try {
            userController.create(user);
        } catch (Exception ignored) {

        }
        user.setPassword(rawPassword);
        return user;
    }

}
