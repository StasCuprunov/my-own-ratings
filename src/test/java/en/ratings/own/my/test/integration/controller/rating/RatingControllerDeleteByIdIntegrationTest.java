package en.ratings.own.my.test.integration.controller.rating;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.model.rating.RatingEntry;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static en.ratings.own.my.test.utility.GeneratorUtility.createNotExistentId;
import static en.ratings.own.my.test.utility.GeneratorUtility.printExceptionMessage;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAccessDeniedException;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException;
import static en.ratings.own.my.test.utility.asserts.AssertThatStatusCodeUtility.assertThatStatusCodeIsNoContent;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        createValidRatingDTODrinksWithNegativeMinimum;
import static en.ratings.own.my.utility.math.MathUtility.isLastIndex;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RatingControllerDeleteByIdIntegrationTest extends RatingControllerIntegrationTest {
    @Test
    public void testValidDeleteByIdWithDrinksAndDeletedRangeOfValues() {
        ResponseEntity<RatingDTO> responseEntity = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        String ratingId = responseEntity.getBody().getId();
        RangeOfValues rangeOfValues = responseEntity.getBody().getRangeOfValues();

        createValidRatingEntriesForDrinksWithNegativeMinimum(ratingId);

        testValidDeleteByIdAndDeletedRangeOfValues(ratingId, rangeOfValues);
    }

    @Test
    public void testValidDeleteByIdWithDrinksAndNotDeletedRangeOfValues() {
        createValidRating(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM);
        ResponseEntity<RatingDTO> responseEntity = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        String ratingId = responseEntity.getBody().getId();
        RangeOfValues rangeOfValues = responseEntity.getBody().getRangeOfValues();

        ArrayList<Rating> listOfRatingsBeforeDelete = findAllRatingRepository();
        ArrayList<RangeOfValues> listOfRangeOfValuesBeforeDelete = findAllRangeOfValuesRepository();
        ArrayList<RatingEntry> listOfRatingEntriesBeforeDelete = findAllRatingEntryRepository();

        createValidRatingEntriesForDrinksWithNegativeMinimum(ratingId);

        testValidDeleteByIdAndNotDeletedRangeOfValues(ratingId, rangeOfValues);

        checkIfSomethingIsMissingThatShouldExistAfterDeleteById(ratingId, listOfRatingsBeforeDelete,
                listOfRangeOfValuesBeforeDelete, listOfRatingEntriesBeforeDelete);
    }

    @Test
    public void testInvalidDeleteByIdWithWrongId() {
        ResponseEntity<RatingDTO> responseEntityCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        String ratingId = responseEntityCreate.getBody().getId();
        createValidRatingEntriesForDrinksWithNegativeMinimum(ratingId);

        ArrayList<Rating> listOfRatingsBeforeDelete = findAllRatingRepository();
        ArrayList<RangeOfValues> listOfRangeOfValuesBeforeDelete = findAllRangeOfValuesRepository();
        ArrayList<RatingEntry> listOfRatingEntriesBeforeDelete = findAllRatingEntryRepository();

        String deleteRatingId = createNotExistentId(ratingId);
        Exception foundException = deleteByIdInvalid(deleteRatingId);

        assertAll(
                () -> assertThatExceptionIsEqualToAccessDeniedException(foundException),
                () -> checkIfSomethingIsMissingThatShouldExistAfterDeleteById(deleteRatingId, listOfRatingsBeforeDelete,
                        listOfRangeOfValuesBeforeDelete, listOfRatingEntriesBeforeDelete)
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
        assertThatExceptionIsEqualToAccessDeniedException(foundException);
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

    private void checkValidDeleteById(String ratingId, ResponseEntity<Object> responseEntity) {
        assertAll("Test valid deleteById:",
                () -> assertThatStatusCodeIsNoContent(responseEntity),
                () -> assertThat(findByIdRatingRepository(ratingId).isEmpty()).isTrue(),
                () -> assertThat(findAllByRatingIdRatingEntryRepository(ratingId).isEmpty()).isTrue()
        );
    }

    private void checkRangeOfValuesAfterDeleteById(RangeOfValues rangeOfValues, boolean shouldRangeOfValuesDeleted) {
        boolean notFoundById = findByIdRangeOfValuesRepository(rangeOfValues.getId()).isEmpty();
        boolean notFoundByAttributes = findByMinimumAndMaximumAndStepWidthRangeOfValuesRepository(
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

    private void
    checkIfSomethingIsMissingThatShouldExistAfterDeleteById(String deleteRatingId,
                                                            ArrayList<Rating> listOfRatingsBeforeDelete,
                                                            ArrayList<RangeOfValues> listOfRangeOfValuesBeforeDelete,
                                                            ArrayList<RatingEntry> listOfRatingEntriesBeforeDelete) {
        checkIfSomethingIsMissingThatShouldExistAfterDeleteById(deleteRatingId, listOfRatingsBeforeDelete,
                listOfRangeOfValuesBeforeDelete, listOfRatingEntriesBeforeDelete, null);
    }
    private void
    checkIfSomethingIsMissingThatShouldExistAfterDeleteById(String deleteRatingId,
                                                            ArrayList<Rating> listOfRatingsBeforeDelete,
                                                            ArrayList<RangeOfValues> listOfRangeOfValuesBeforeDelete,
                                                            ArrayList<RatingEntry> listOfRatingEntriesBeforeDelete,
                                                            RangeOfValues deletedRangeOfValues) {
        assertAll(
                () -> assertThat(isRatingMissingThatShouldExistAfterDeleteById(deleteRatingId,
                        listOfRatingsBeforeDelete)).isFalse(),
                () -> assertThat(isRangeOfValuesThatShouldExistAfterDeleteById(deletedRangeOfValues,
                        listOfRangeOfValuesBeforeDelete)).isFalse(),
                () -> assertThat(isRatingEntryMissingThatShouldExistAfterDeleteById(deleteRatingId,
                        listOfRatingEntriesBeforeDelete)).isFalse()
        );
    }

    private boolean
    isRatingMissingThatShouldExistAfterDeleteById(String deleteRatingId,
                                                  ArrayList<Rating> listOfRatingsBeforeDelete) {
        ArrayList<Rating> listOfRatingsAfterDelete = findAllRatingRepository();
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

    private boolean
    isRangeOfValuesThatShouldExistAfterDeleteById(RangeOfValues deletedRangeOfValues,
                                                  ArrayList<RangeOfValues> listOfRangeOfValuesBeforeDelete) {
        ArrayList<RangeOfValues> listOfRangeOfValuesAfterDelete = findAllRangeOfValuesRepository();
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

    private boolean
    isRatingEntryMissingThatShouldExistAfterDeleteById(String deleteRatingId,
                                                       ArrayList<RatingEntry> listOfRatingEntriesBeforeDelete) {
        ArrayList<RatingEntry> listOfRatingEntriesAfterDelete = findAllRatingEntryRepository();
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


    private ResponseEntity<Object> deleteByIdSuccessful(String id) {
        try {
            return ratingController.deleteById(id);
        } catch (Exception e) {
            printExceptionMessage(e);
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
}
