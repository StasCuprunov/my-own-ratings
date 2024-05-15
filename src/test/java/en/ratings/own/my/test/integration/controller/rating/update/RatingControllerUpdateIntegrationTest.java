package en.ratings.own.my.test.integration.controller.rating.update;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.model.rating.RatingEntry;
import en.ratings.own.my.test.integration.controller.rating.RatingControllerIntegrationTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static en.ratings.own.my.test.utility.GeneratorUtility.printExceptionMessage;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAccessDeniedException;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingUpdateFailedException;
import static en.ratings.own.my.test.utility.asserts.AssertThatUtility.assertThatIdIsDefined;
import static en.ratings.own.my.test.utility.asserts.AssertThatStatusCodeUtility.assertThatStatusCodeIsOk;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RatingControllerUpdateIntegrationTest extends RatingControllerIntegrationTest {

    protected void testValidUpdate(RatingDTO input, ResponseEntity<RatingDTO> responseEntity) {
        RatingDTO result = responseEntity.getBody();
        assertAll("Test valid update rating:",
                () -> assertThatStatusCodeIsOk(responseEntity),
                () -> compareValidInputWithResultAfterUpdate(input, result),
                () -> compareValidInputWithDatabase(input)
        );
    }

    protected void compareValidInputWithResultAfterUpdate(RatingDTO input, RatingDTO result) {
        assertAll(
                () -> assertThat(result.getId()).isEqualTo(input.getId()),
                () -> assertThat(result.getUserId()).isEqualTo(input.getUserId()),
                () -> assertThat(result.getName()).isEqualTo(input.getName()),
                () -> assertThat(result.getDescription()).isEqualTo(input.getDescription()),
                () -> assertThatIdIsDefined(result.getRangeOfValues().getId()),
                () -> assertThat(result.getRangeOfValues().equalsWithoutId(input.getRangeOfValues())).isTrue(),
                () -> assertThat(result.getRatingEntries()).isNull()
        );
    }

    protected void compareValidInputWithDatabase(RatingDTO input) {
        String rangeOfValuesId = compareValidInputWithRatingTable(input);
        compareValidInputWithRangeOfValuesTable(input, rangeOfValuesId);
    }

    protected void compareIfDatabaseEntriesHasNotBeenChanged(RatingDTO input) {
        compareValidInputWithDatabase(input);
    }

    protected String compareValidInputWithRatingTable(RatingDTO input) {
        Optional<Rating> storedRatingResult = findByIdRatingRepository(input.getId());
        Rating storedRating = storedRatingResult.get();
        String rangeOfValuesId = storedRating.getRangeOfValuesId();
        assertAll(
                () -> assertThat(storedRatingResult).isPresent(),
                () -> assertThat(storedRating.getId()).isEqualTo(input.getId()),
                () -> assertThat(storedRating.getUserId()).isEqualTo(input.getUserId()),
                () -> assertThatIdIsDefined(rangeOfValuesId),
                () -> assertThat(storedRating.getName()).isEqualTo(input.getName()),
                () -> assertThat(storedRating.getDescription()).isEqualTo(input.getDescription())
        );
        return rangeOfValuesId;
    }

    protected void compareValidInputWithRangeOfValuesTable(RatingDTO input, String rangeOfValuesId) {
        RangeOfValues rangeOfValuesInput = input.getRangeOfValues();
        Optional<RangeOfValues> storedRangeOfValuesResult = findByIdRangeOfValuesRepository(rangeOfValuesId);
        RangeOfValues storedRangeOfValues = storedRangeOfValuesResult.get();

        assertAll(
                () -> assertThat(storedRangeOfValuesResult).isPresent(),
                () -> assertThat(storedRangeOfValues.equalsWithoutId(rangeOfValuesInput)).isTrue(),
                () -> compareIfRangeOfValuesExistsExactOnce(input)
        );
    }

    protected void checkIfRatingEntriesInInputAreCreatedWithUpdate(RatingDTO input) {
        for (RatingEntry storedRatingEntry: findAllRatingEntryRepository()) {
            for (RatingEntry inputRatingEntry: input.getRatingEntries()) {
                assertThat(inputRatingEntry).isNotEqualTo(storedRatingEntry);
            }
        }
    }

    protected void checkIfOldRangeOfValuesIsDeleted(RangeOfValues oldRangeOfValues) {
        checkOldRangeOfValuesAfterUpdate(oldRangeOfValues, true);
    }

    protected void checkIfOldRangeOfValuesIsAvailable(RangeOfValues oldRangeOfValues) {
        checkOldRangeOfValuesAfterUpdate(oldRangeOfValues, false);
    }

    protected void checkOldRangeOfValuesAfterUpdate(RangeOfValues oldRangeOfValues, boolean shouldBeDeleted) {
        boolean noRangeOfValuesFound = findByMinimumAndMaximumAndStepWidthRangeOfValuesRepository(oldRangeOfValues).
                isEmpty();
        if (shouldBeDeleted) {
            assertThat(noRangeOfValuesFound).isTrue();
        }
        else {
            assertThat(noRangeOfValuesFound).isFalse();
        }
    }

    protected ResponseEntity<RatingDTO> updateSuccessful(RatingDTO input) {
        try {
            return ratingController.update(input);
        } catch (Exception e) {
            printExceptionMessage(e);
        }
        return null;
    }

    protected Exception updateInvalid(RatingDTO input) {
        try {
            ratingController.update(input);
        } catch (Exception e) {
            return e;
        }
        return new Exception();
    }

    protected void testUpdateInvalidWithExpectedRatingUpdateFailedException(RatingDTO input) {
        Exception foundException = updateInvalid(input);
        assertThatExceptionIsEqualToRatingUpdateFailedException(foundException);
    }

    protected void testUpdateInvalidWithExceptedAuthenticationCredentialsNotFoundException(RatingDTO input) {
        Exception foundException = updateInvalid(input);
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException(foundException);
    }

    protected void testUpdateInvalidWithExceptedAccessDeniedException(RatingDTO input) {
        Exception foundException = updateInvalid(input);
        assertThatExceptionIsEqualToAccessDeniedException(foundException);
    }

    protected RangeOfValues createNewRangeOfValuesObject(RangeOfValues rangeOfValues) {
        return new RangeOfValues(rangeOfValues.getId(), rangeOfValues.getMinimum(), rangeOfValues.getMaximum(),
                rangeOfValues.getStepWidth());
    }

    protected RatingDTO createNewRatingDTOObject(RatingDTO ratingDTO) {
        return new RatingDTO(ratingDTO.getId(), ratingDTO.getUserId(), ratingDTO.getName(),
                ratingDTO.getDescription(), ratingDTO.getRangeOfValues(), ratingDTO.getRatingEntries());
    }
}
