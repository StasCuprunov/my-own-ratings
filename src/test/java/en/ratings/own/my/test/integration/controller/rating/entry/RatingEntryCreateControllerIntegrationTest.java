package en.ratings.own.my.test.integration.controller.rating.entry;

import en.ratings.own.my.model.rating.RatingEntry;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static en.ratings.own.my.test.integration.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingEntryFailedException;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatUtility.assertThatIdIsDefined;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatUtility.assertThatStatusCodeIsCreated;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RatingEntryCreateControllerIntegrationTest extends RatingEntryControllerIntegrationTest {

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
        } catch(Exception ignored) {

        }
        return null;
    }
}
