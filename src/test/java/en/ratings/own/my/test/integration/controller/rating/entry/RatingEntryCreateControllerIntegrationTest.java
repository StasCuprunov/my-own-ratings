package en.ratings.own.my.test.integration.controller.rating.entry;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RatingEntry;
import org.springframework.http.ResponseEntity;
import org.junit.Test;

import java.util.Optional;

import static en.ratings.own.my.test.constant.TestConstants.EMPTY_TEXT;
import static en.ratings.own.my.test.constant.TestConstants.EXPECTED_ZERO;
import static en.ratings.own.my.test.utility.GeneratorUtility.ID_TEST;
import static en.ratings.own.my.test.utility.GeneratorUtility.numberBetweenRangeOfValuesButNotAllowed;
import static en.ratings.own.my.test.utility.GeneratorUtility.numberGreaterThanMaximum;
import static en.ratings.own.my.test.utility.GeneratorUtility.numberSmallerThanMinimum;
import static en.ratings.own.my.test.utility.GeneratorUtility.printExceptionMessage;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAccessDeniedException;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingEntryFailedException;
import static en.ratings.own.my.test.utility.asserts.AssertThatUtility.assertThatIdIsDefined;
import static en.ratings.own.my.test.utility.asserts.AssertThatStatusCodeUtility.assertThatStatusCodeIsCreated;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        createValidRangeOfValuesWithNegativeMinimum;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        createValidRatingEntryCokeForDrinksWithAmazonRating;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        createValidRatingEntryCokeForDrinksWithNegativeMinimum;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RatingEntryCreateControllerIntegrationTest extends RatingEntryControllerIntegrationTest {

    @Test
    public void testValidCreateDrinks() {
        ResponseEntity<RatingDTO> responseEntity = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        String ratingId = responseEntity.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        testCreateSuccessful(ratingEntry, createSuccessful(ratingEntry));
    }

    @Test
    public void testValidCreateWithSameNameButDifferentUsers() {
        ResponseEntity<RatingDTO> responseEntity = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        String ratingId = responseEntity.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        createSuccessful(ratingEntry);

        ResponseEntity<RatingDTO> responseEntityTest = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingIdTest =  responseEntityTest.getBody().getId();
        RatingEntry ratingEntryTest = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingIdTest);
        testCreateSuccessful(ratingEntryTest, createSuccessful(ratingEntryTest));
    }

    @Test
    public void testValidCreateWithSameNameAndSameUserButDifferentRatings() {
        ResponseEntity<RatingDTO> responseEntity = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        String ratingId = responseEntity.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        createSuccessful(ratingEntry);

        ResponseEntity<RatingDTO> responseEntityTest = createValidRatingDrinkInAsiaWithAmazonRating(userStevenWorm);
        String ratingIdTest = responseEntityTest.getBody().getId();
        RatingEntry ratingEntryTest = createValidRatingEntryCokeForDrinksWithAmazonRating(ratingIdTest);
        testCreateSuccessful(ratingEntryTest, createSuccessful(ratingEntryTest));
    }

    @Test
    public void testInvalidCreateWithoutLoggedIn() {
        ResponseEntity<RatingDTO> responseEntity = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        logout();
        String ratingId = responseEntity.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException(createInvalid(ratingEntry));
        checkIfExpectedNumberOfRatingEntriesWithRatingIdAreAvailable(ratingId, EXPECTED_ZERO);
    }

    @Test
    public void testInvalidCreateWithUnauthorizedUserWithRatingId() {
        ResponseEntity<RatingDTO> responseEntity = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        login(userFalakNoorahKhoury);
        String ratingId = responseEntity.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        assertThatExceptionIsEqualToAccessDeniedException(createInvalid(ratingEntry));
        checkIfExpectedNumberOfRatingEntriesWithRatingIdAreAvailable(ratingId, EXPECTED_ZERO);
    }

    @Test
    public void testInvalidCreateWithDefinedId() {
        ResponseEntity<RatingDTO> responseEntity = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        String ratingId = responseEntity.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        ratingEntry.setId(ID_TEST);
        assertThatExceptionIsEqualToRatingEntryFailedException(createInvalid(ratingEntry));
        checkIfExpectedNumberOfRatingEntriesWithRatingIdAreAvailable(ratingId, EXPECTED_ZERO);
    }

    @Test
    public void testInvalidCreateWithEmptyName() {
        ResponseEntity<RatingDTO> responseEntity = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        String ratingId = responseEntity.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        ratingEntry.setName(EMPTY_TEXT);
        assertThatExceptionIsEqualToRatingEntryFailedException(createInvalid(ratingEntry));
        checkIfExpectedNumberOfRatingEntriesWithRatingIdAreAvailable(ratingId, EXPECTED_ZERO);
    }

    @Test
    public void testInvalidCreateWithAlreadyUsedNameInRating() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        String ratingId = responseEntityRating.getBody().getId();
        ResponseEntity<RatingEntry> responseEntityRatingEntry =
                createSuccessful(createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId));
        RatingEntry oldRatingEntry = createNewRatingEntryObject(responseEntityRatingEntry.getBody());
        RatingEntry ratingEntry = responseEntityRatingEntry.getBody();
        ratingEntry.setValue(createValidRangeOfValuesWithNegativeMinimum().getMaximum());
        assertThatExceptionIsEqualToRatingEntryFailedException(createInvalid(ratingEntry));
        checkIfOldRatingEntryHasChangedAfterCreateWithSameRatingEntryNameAndSameRating(oldRatingEntry);
    }

    @Test
    public void testInvalidCreateWithValueSmallerThanMinimum() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        ratingEntry.setValue(numberSmallerThanMinimum(createValidRangeOfValuesWithNegativeMinimum().getMinimum()));
        assertThatExceptionIsEqualToRatingEntryFailedException(createInvalid(ratingEntry));
        checkIfExpectedNumberOfRatingEntriesWithRatingIdAreAvailable(ratingId, EXPECTED_ZERO);
    }

    @Test
    public void testInvalidCreateWithValueGreaterThanMaximum() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        ratingEntry.setValue(numberGreaterThanMaximum(createValidRangeOfValuesWithNegativeMinimum().getMaximum()));
        assertThatExceptionIsEqualToRatingEntryFailedException(createInvalid(ratingEntry));
        checkIfExpectedNumberOfRatingEntriesWithRatingIdAreAvailable(ratingId, EXPECTED_ZERO);
    }

    @Test
    public void testInvalidCreateWithValueBetweenRangeOfValuesButNotAllowedValue() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        Double notAllowedValue = numberBetweenRangeOfValuesButNotAllowed(createValidRangeOfValuesWithNegativeMinimum());
        ratingEntry.setValue(notAllowedValue);
        assertThatExceptionIsEqualToRatingEntryFailedException(createInvalid(ratingEntry));
        checkIfExpectedNumberOfRatingEntriesWithRatingIdAreAvailable(ratingId, EXPECTED_ZERO);
    }

    private void testCreateSuccessful(RatingEntry input, ResponseEntity<RatingEntry> responseEntity) {
        RatingEntry result = responseEntity.getBody();
        assertAll("Test create rating entry successful:",
                () -> assertThatStatusCodeIsCreated(responseEntity),
                () -> compareInputWithResult(input, result),
                () -> compareInputWithDatabase(input, result.getId())
        );
    }

    private void compareInputWithResult(RatingEntry input, RatingEntry result) {
        assertAll(
                () -> assertThatIdIsDefined(result.getId()),
                () -> assertThat(result.getRatingId()).isEqualTo(input.getRatingId()),
                () -> assertThat(result.getName()).isEqualTo(input.getName()),
                () -> assertThat(result.getValue()).isEqualTo(input.getValue())
        );
    }

    private void compareInputWithDatabase(RatingEntry input, String ratingEntryId) {
        Optional<RatingEntry> foundRatingEntry = findByIdRatingEntryRepository(ratingEntryId);
        RatingEntry storedRatingEntry = foundRatingEntry.get();
        assertAll(
                () -> assertThat(storedRatingEntry.getRatingId()).isEqualTo(input.getRatingId()),
                () -> assertThat(storedRatingEntry.getName()).isEqualTo(input.getName()),
                () -> assertThat(storedRatingEntry.getValue()).isEqualTo(input.getValue())
        );
    }

    private void checkIfExpectedNumberOfRatingEntriesWithRatingIdAreAvailable(String ratingId,
                                                                           int expectedNumberOfRatingEntries) {
        assertThat(findAllByRatingIdRatingEntryRepository(ratingId).size()).isEqualTo(expectedNumberOfRatingEntries);
    }

    private void checkIfOldRatingEntryHasChangedAfterCreateWithSameRatingEntryNameAndSameRating
            (RatingEntry oldRatingEntry) {
        RatingEntry storedRatingEntry = findByIdRatingEntryRepository(oldRatingEntry.getId()).get();

        assertAll(
                () -> assertThat(storedRatingEntry.getId()).isEqualTo(oldRatingEntry.getId()),
                () -> assertThat(storedRatingEntry.getRatingId()).isEqualTo(oldRatingEntry.getRatingId()),
                () -> assertThat(storedRatingEntry.getName()).isEqualTo(oldRatingEntry.getName()),
                () -> assertThat(storedRatingEntry.getValue()).isEqualTo(oldRatingEntry.getValue())
        );
    }

    private Exception createInvalid(RatingEntry input) {
        try {
            ratingEntryController.create(input);
        } catch (Exception e) {
            return e;
        }
        return new Exception();
    }

    private ResponseEntity<RatingEntry> createSuccessful(RatingEntry input) {
        try {
            return ratingEntryController.create(input);
        } catch (Exception e) {
            printExceptionMessage(e);
        }
        return null;
    }
}
