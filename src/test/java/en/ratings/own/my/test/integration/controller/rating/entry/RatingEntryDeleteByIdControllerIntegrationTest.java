package en.ratings.own.my.test.integration.controller.rating.entry;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RatingEntry;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static en.ratings.own.my.test.utility.GeneratorUtility.createNotExistentId;
import static en.ratings.own.my.test.utility.GeneratorUtility.printExceptionMessage;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingEntryByIdNotFoundException;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingEntryDeleteByIdNotAllowedException;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        createValidRatingEntryCokeForDrinksWithNegativeMinimum;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RatingEntryDeleteByIdControllerIntegrationTest extends RatingEntryControllerIntegrationTest {

    @Test
    public void testValidDeleteByIdWithDrinks() {

    }

    @Test
    public void testInvalidDeleteByIdWithoutLoggedIn() {
        ResponseEntity<RatingDTO> responseEntity = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntity.getBody().getId();
        RatingEntry ratingEntry =
                saveRatingEntryRepository(createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId));
        String ratingEntryId = ratingEntry.getId();
        logout();
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException(deleteByIdInvalid(ratingEntryId));
        checkIfRatingEntryHasBeenNotDeletedAndEdited(ratingEntryId, ratingEntry);
    }

    @Test
    public void testInvalidDeleteByIdWithUnauthorizedUser() {
        ResponseEntity<RatingDTO> responseEntity = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntity.getBody().getId();
        RatingEntry ratingEntry =
                saveRatingEntryRepository(createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId));
        String ratingEntryId = ratingEntry.getId();
        login(userStevenWorm);
        assertThatExceptionIsEqualToRatingEntryDeleteByIdNotAllowedException(deleteByIdInvalid(ratingEntryId));
        checkIfRatingEntryHasBeenNotDeletedAndEdited(ratingEntryId, ratingEntry);

    }

    @Test
    public void testInvalidDeleteByIdWithNotExistentId() {
        ResponseEntity<RatingDTO> responseEntity = createRatingDrinksWithNegativeMinimum(userFalakNoorahKhoury);
        String ratingId = responseEntity.getBody().getId();
        RatingEntry ratingEntry =
                saveRatingEntryRepository(createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId));
        String ratingEntryId = ratingEntry.getId();
        String nonExistentRatingEntryId = createNotExistentId(ratingEntryId);
        assertThatExceptionIsEqualToRatingEntryByIdNotFoundException(deleteByIdInvalid(nonExistentRatingEntryId));
        checkIfRatingEntryHasBeenNotDeletedAndEdited(ratingEntryId, ratingEntry);
    }

    private ResponseEntity<Object> deleteByIdSuccessful(String id) {
        try {
            return ratingEntryController.deleteById(id);
        } catch (Exception e) {
            printExceptionMessage(e);
        }
        return null;
    }

    private Exception deleteByIdInvalid(String id) {
        try {
            ratingEntryController.deleteById(id);
        } catch (Exception e) {
            return e;
        }
        return new Exception();
    }

    private void checkIfRatingEntryHasBeenNotDeletedAndEdited(String id, RatingEntry shouldStoredRatingEntry) {
        RatingEntry foundRatingEntry = ratingEntryRepository.findById(id).get();

        assertAll(
                () -> assertThat(foundRatingEntry.getId()).isEqualTo(shouldStoredRatingEntry.getId()),
                () -> assertThat(foundRatingEntry.getRatingId()).isEqualTo(shouldStoredRatingEntry.getRatingId()),
                () -> assertThat(foundRatingEntry.getName()).isEqualTo(shouldStoredRatingEntry.getName()),
                () -> assertThat(foundRatingEntry.getValue()).isEqualTo(shouldStoredRatingEntry.getValue())
        );
    }
}
