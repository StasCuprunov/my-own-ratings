package en.ratings.own.my.test.integration.controller.rating.entry;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RatingEntry;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static en.ratings.own.my.test.constant.TestConstants.NUMBER_WITH_TOO_MANY_DECIMAL_DIGITS;
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
import static en.ratings.own.my.test.utility.asserts.AssertThatStatusCodeUtility.assertThatStatusCodeIsOk;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        createValidRangeOfValuesWithNegativeMinimum;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.DRINK_APPLE_JUICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RatingEntryUpdateControllerIntegrationTest extends RatingEntryControllerIntegrationTest {

    @Test
    public void testValidUpdateWithName() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntryBeforeUpdate = saveValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        RatingEntry input = createNewRatingEntryObject(ratingEntryBeforeUpdate);
        input.setName(DRINK_APPLE_JUICE);
        testValidUpdate(input, updateSuccessful(input));
    }

    @Test
    public void testValidUpdateWithValue() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntryBeforeUpdate = saveValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        RatingEntry input = createNewRatingEntryObject(ratingEntryBeforeUpdate);
        input.setValue(createValidRangeOfValuesWithNegativeMinimum().getMinimum());
        testValidUpdate(input, updateSuccessful(input));
    }

    @Test
    public void testValidUpdateWithNameAndValue() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntryBeforeUpdate = saveValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        RatingEntry input = createNewRatingEntryObject(ratingEntryBeforeUpdate);
        input.setName(DRINK_APPLE_JUICE);
        input.setValue(createValidRangeOfValuesWithNegativeMinimum().getMinimum());
        testValidUpdate(input, updateSuccessful(input));
    }

    @Test
    public void testValidUpdateWithNameUsedByOtherUser() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userStevenWorm);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntryFromOtherUser = saveValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);

        responseEntityRating = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntryBeforeUpdate = saveValidRatingEntryAppleJuiceForDrinksWithNegativeMinimum(ratingId);

        RatingEntry input = createNewRatingEntryObject(ratingEntryBeforeUpdate);
        input.setName(ratingEntryFromOtherUser.getName());
        input.setValue(createValidRangeOfValuesWithNegativeMinimum().getMinimum());

        testValidUpdate(input, updateSuccessful(input));
    }

    @Test
    public void testInvalidUpdateWithoutLoggedIn() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntryBeforeUpdate = saveValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        logout();
        RatingEntry input = createNewRatingEntryObject(ratingEntryBeforeUpdate);
        input.setName(DRINK_APPLE_JUICE);
        testInvalidUpdateWithExpectedAuthenticationCredentialsNotFoundException(input, ratingEntryBeforeUpdate);
    }

    @Test
    public void testInvalidUpdateWithNotExistentId() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntryBeforeUpdate = saveValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        RatingEntry input = createNewRatingEntryObject(ratingEntryBeforeUpdate);
        input.setId(input.getId() + ID_TEST);
        testInvalidUpdateWithExpectedAccessDeniedException(input, ratingEntryBeforeUpdate);
    }

    @Test
    public void testInvalidUpdateWithUnauthorizedUserBecauseRatingId() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntryBeforeUpdate = saveValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        RatingEntry input = createNewRatingEntryObject(ratingEntryBeforeUpdate);
        input.setName(DRINK_APPLE_JUICE);
        login(userStevenWorm);
        testInvalidUpdateWithExpectedAccessDeniedException(input, ratingEntryBeforeUpdate);
    }

    @Test
    public void testInvalidUpdateWithAlreadyUsedNameInRating() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntityRating.getBody().getId();

        RatingEntry ratingEntryBeforeUpdate = saveValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);

        RatingEntry input = saveValidRatingEntryRedBullForDrinksWithNegativeMinimum(ratingId);
        input.setName(ratingEntryBeforeUpdate.getName());
        testInvalidUpdateWithExpectedRatingEntryFailedException(input, ratingEntryBeforeUpdate);
    }

    @Test
    public void testInvalidUpdateWithValueHasTooManyDecimalDigits() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntryBeforeUpdate = saveValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        RatingEntry input = createNewRatingEntryObject(ratingEntryBeforeUpdate);
        Double value = createValidRangeOfValuesWithNegativeMinimum().getMinimum() + NUMBER_WITH_TOO_MANY_DECIMAL_DIGITS;
        input.setValue(numberSmallerThanMinimum(value));
        testInvalidUpdateWithExpectedRatingEntryFailedException(input, ratingEntryBeforeUpdate);
    }

    @Test
    public void testInvalidUpdateWithValueSmallerThanMinimum() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntryBeforeUpdate = saveValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        RatingEntry input = createNewRatingEntryObject(ratingEntryBeforeUpdate);
        input.setValue(numberSmallerThanMinimum(createValidRangeOfValuesWithNegativeMinimum().getMinimum()));
        testInvalidUpdateWithExpectedRatingEntryFailedException(input, ratingEntryBeforeUpdate);
    }

    @Test
    public void testInvalidUpdateWithValueGreaterThanMaximum() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntryBeforeUpdate = saveValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        RatingEntry input = createNewRatingEntryObject(ratingEntryBeforeUpdate);
        input.setValue(numberGreaterThanMaximum(createValidRangeOfValuesWithNegativeMinimum().getMaximum()));
        testInvalidUpdateWithExpectedRatingEntryFailedException(input, ratingEntryBeforeUpdate);
    }

    @Test
    public void testInvalidUpdateWithValueBetweenRangOfValueButNotAllowed() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntryBeforeUpdate = saveValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        RatingEntry input = createNewRatingEntryObject(ratingEntryBeforeUpdate);
        input.setValue(numberBetweenRangeOfValuesButNotAllowed(createValidRangeOfValuesWithNegativeMinimum()));
        testInvalidUpdateWithExpectedRatingEntryFailedException(input, ratingEntryBeforeUpdate);
    }

    private void
    testInvalidUpdateWithExpectedAuthenticationCredentialsNotFoundException(RatingEntry input,
                                                                            RatingEntry ratingEntryBefore) {
        assertAll(
                () -> assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException(updateInvalid(input)),
                () -> compareCreatedRatingEntryBeforeAndAfterFailedUpdate(ratingEntryBefore)
        );
    }

    private void testInvalidUpdateWithExpectedRatingEntryFailedException(RatingEntry input,
                                                                         RatingEntry ratingEntryBefore) {
        Exception fondException = updateInvalid(input);
        printExceptionMessage(fondException);
        assertAll(
                () -> assertThatExceptionIsEqualToRatingEntryFailedException(fondException),
                () -> compareCreatedRatingEntryBeforeAndAfterFailedUpdate(ratingEntryBefore)
        );
    }

    private void testInvalidUpdateWithExpectedAccessDeniedException(RatingEntry input, RatingEntry ratingEntryBefore) {
        assertAll(
                () -> assertThatExceptionIsEqualToAccessDeniedException(updateInvalid(input)),
                () -> compareCreatedRatingEntryBeforeAndAfterFailedUpdate(ratingEntryBefore)
        );
    }

    private void compareCreatedRatingEntryBeforeAndAfterFailedUpdate(RatingEntry ratingEntryBefore) {
        RatingEntry foundRatingEntry = findByIdRatingEntryRepository(ratingEntryBefore.getId()).get();

        assertAll(
                () -> assertThat(foundRatingEntry.getId()).isEqualTo(ratingEntryBefore.getId()),
                () -> assertThat(foundRatingEntry.getRatingId()).isEqualTo(ratingEntryBefore.getRatingId()),
                () -> assertThat(foundRatingEntry.getName()).isEqualTo(ratingEntryBefore.getName()),
                () -> assertThat(foundRatingEntry.getValue()).isEqualTo(ratingEntryBefore.getValue())
        );
    }

    private void testValidUpdate(RatingEntry input, ResponseEntity<RatingEntry> responseEntity) {
        RatingEntry result = responseEntity.getBody();
        assertAll("Test valid update rating entry",
                () -> assertThatStatusCodeIsOk(responseEntity),
                () -> compareInputWithResult(input, result),
                () -> compareInputWithDatabase(input)
        );
    }

    private void compareInputWithResult(RatingEntry input, RatingEntry result) {
        assertAll(
                () -> assertThat(result.getId()).isEqualTo(input.getId()),
                () -> assertThat(result.getRatingId()).isEqualTo(input.getRatingId()),
                () -> assertThat(result.getName()).isEqualTo(input.getName()),
                () -> assertThat(result.getValue()).isEqualTo(input.getValue())
        );
    }

    private void compareInputWithDatabase(RatingEntry input) {
        RatingEntry foundRatingEntry = findByIdRatingEntryRepository(input.getId()).get();
        compareInputWithResult(input, foundRatingEntry);
    }

    private ResponseEntity<RatingEntry> updateSuccessful(RatingEntry input) {
        try {
            return ratingEntryController.update(input);
        } catch (Exception e) {
            printExceptionMessage(e);
        }
        return null;
    }

    private Exception updateInvalid(RatingEntry input) {
        try {
            ratingEntryController.update(input);
        } catch (Exception e) {
            return e;
        }
        return new Exception();
    }
}
