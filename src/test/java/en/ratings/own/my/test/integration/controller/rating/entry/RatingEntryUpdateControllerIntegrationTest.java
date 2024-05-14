package en.ratings.own.my.test.integration.controller.rating.entry;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RatingEntry;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static en.ratings.own.my.test.utility.GeneratorUtility.ID_TEST;
import static en.ratings.own.my.test.utility.GeneratorUtility.numberBetweenRangeOfValuesButNotAllowed;
import static en.ratings.own.my.test.utility.GeneratorUtility.numberGreaterThanMaximum;
import static en.ratings.own.my.test.utility.GeneratorUtility.numberSmallerThanMinimum;
import static en.ratings.own.my.test.utility.GeneratorUtility.printExceptionMessage;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingEntryFailedException;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingEntryUpdateNotAllowedException;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        createValidRangeOfValuesWithNegativeMinimum;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.DRINK_APPLE_JUICE;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        createValidRatingEntryRedBullForDrinksWithNegativeMinimum;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RatingEntryUpdateControllerIntegrationTest extends RatingEntryControllerIntegrationTest {

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
        testInvalidUpdateWithExpectedRatingEntryFailedException(input, ratingEntryBeforeUpdate);
    }

    @Test
    public void testInvalidUpdateWithUnauthorizedUserBecauseRatingId() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntryBeforeUpdate = saveValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        RatingEntry input = createNewRatingEntryObject(ratingEntryBeforeUpdate);
        input.setName(DRINK_APPLE_JUICE);
        login(userStevenWorm);
        testInvalidUpdateWithExpectedRatingEntryUpdateNotAllowedException(input, ratingEntryBeforeUpdate);
    }

    @Test
    public void testInvalidUpdateWithAlreadyUsedNameInRating() {
        ResponseEntity<RatingDTO> responseEntityRating = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntityRating.getBody().getId();
        RatingEntry ratingEntryBeforeUpdate = saveValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId);
        RatingEntry input =
                createNewRatingEntryObject(createValidRatingEntryRedBullForDrinksWithNegativeMinimum(ratingId));
        input.setName(ratingEntryBeforeUpdate.getName());
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
        assertAll(
                () -> assertThatExceptionIsEqualToRatingEntryFailedException(updateInvalid(input)),
                () -> compareCreatedRatingEntryBeforeAndAfterFailedUpdate(ratingEntryBefore)
        );
    }

    private void testInvalidUpdateWithExpectedRatingEntryUpdateNotAllowedException(RatingEntry input,
                                                                                   RatingEntry ratingEntryBefore) {
        assertAll(
                () -> assertThatExceptionIsEqualToRatingEntryUpdateNotAllowedException(updateInvalid(input)),
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
