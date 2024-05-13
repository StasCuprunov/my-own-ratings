package en.ratings.own.my.test.integration.controller.rating.entry;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RatingEntry;
import org.springframework.http.ResponseEntity;
import org.junit.Test;

import java.util.Optional;

import static en.ratings.own.my.test.integration.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingEntryCreateNotAllowedException;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingEntryFailedException;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatUtility.assertThatIdIsDefined;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatUtility.assertThatStatusCodeIsCreated;
import static en.ratings.own.my.test.integration.utility.rating.CreateRangeOfValuesUtility.
        createValidRangeOfValuesWithNegativeMinimum;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        createValidRatingEntryCokeForDrinksWithNegativeMinimum;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RatingEntryCreateControllerIntegrationTest extends RatingEntryControllerIntegrationTest {

    private static final int NO_RESULTS_EXPECTED = 0;

    @Test
    public void testValidCreateDrinks() {

    }

    @Test
    public void testValidCreateWithSameNameButDifferentUsers() {

    }

    @Test
    public void testValidCreateWithSameNameAndSameUserButDifferentRatings() {

    }

    @Test
    public void testInvalidCreateWithoutLoggedIn() {
        ResponseEntity<RatingDTO> responseEntity = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        logout();
        String ratingId = responseEntity.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        testCreateInvalidWithExpectedAuthenticationCredentialsNotFoundException(ratingEntry);
        checkIfExpectedNumberOfRatingEntriesWithRatingIdAreAvailable(ratingId, NO_RESULTS_EXPECTED);
    }

    @Test
    public void testInvalidCreateWithUnauthorizedUserWithRatingId() {
        ResponseEntity<RatingDTO> responseEntity = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        login(userFalakNoorahKhoury);
        String ratingId = responseEntity.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        testCreateInvalidWithExpectedRatingEntryCreateNotAllowedException(ratingEntry);
        checkIfExpectedNumberOfRatingEntriesWithRatingIdAreAvailable(ratingId, NO_RESULTS_EXPECTED);
    }

    @Test
    public void testInvalidCreateWithEmptyName() {
        ResponseEntity<RatingDTO> responseEntity = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        String ratingId = responseEntity.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        ratingEntry.setName("       \n ");
        testCreateInvalidWithExpectedRatingEntryFailedException(ratingEntry);
        checkIfExpectedNumberOfRatingEntriesWithRatingIdAreAvailable(ratingId, NO_RESULTS_EXPECTED);
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
        testCreateInvalidWithExpectedRatingEntryFailedException(ratingEntry);
        checkIfOldRatingEntryHasChangedAfterCreateWithSameRatingEntryNameAndSameRating(oldRatingEntry);
    }

    @Test
    public void testInvalidCreateWithValueSmallerThanMinimum() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        ratingEntry.setValue(createValidRangeOfValuesWithNegativeMinimum().getMinimum() - 1);
        testCreateInvalidWithExpectedRatingEntryFailedException(ratingEntry);
        checkIfExpectedNumberOfRatingEntriesWithRatingIdAreAvailable(ratingId, NO_RESULTS_EXPECTED);
    }

    @Test
    public void testInvalidCreateWithValueGreaterThanMaximum() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        ratingEntry.setValue(createValidRangeOfValuesWithNegativeMinimum().getMaximum() + 0.5);
        testCreateInvalidWithExpectedRatingEntryFailedException(ratingEntry);
        checkIfExpectedNumberOfRatingEntriesWithRatingIdAreAvailable(ratingId, NO_RESULTS_EXPECTED);
    }

    @Test
    public void testInvalidCreateWithValueBetweenRangeOfValuesButNotAllowedValue() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntry = createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        Double notAllowedValue = createValidRangeOfValuesWithNegativeMinimum().getMinimum() +
                createValidRangeOfValuesWithNegativeMinimum().getStepWidth() / 2.0;
        ratingEntry.setValue(notAllowedValue);
        testCreateInvalidWithExpectedRatingEntryFailedException(ratingEntry);
        checkIfExpectedNumberOfRatingEntriesWithRatingIdAreAvailable(ratingId, NO_RESULTS_EXPECTED);
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
    private void testCreateInvalidWithExpectedAuthenticationCredentialsNotFoundException(RatingEntry input) {
        Exception foundException = createInvalid(input);
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException(foundException);
    }

    private void testCreateInvalidWithExpectedRatingEntryFailedException(RatingEntry input) {
        Exception foundException = createInvalid(input);
        assertThatExceptionIsEqualToRatingEntryFailedException(foundException);
    }

    private void testCreateInvalidWithExpectedRatingEntryCreateNotAllowedException(RatingEntry input) {
        Exception foundException = createInvalid(input);
        assertThatExceptionIsEqualToRatingEntryCreateNotAllowedException(foundException);
    }

    private void checkIfExpectedNumberOfRatingEntriesWithRatingIdAreAvailable(String ratingId,
                                                                           int expectedNumberOfRatingEntries) {
        assertThat(ratingEntryRepository.findAllByRatingId(ratingId).size()).isEqualTo(expectedNumberOfRatingEntries);
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
            System.out.println(e.getMessage());
        }
        return null;
    }
}
