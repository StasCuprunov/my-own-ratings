package en.ratings.own.my.test.integration.controller.rating;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RatingEntry;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static en.ratings.own.my.test.integration.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingByIdNotAllowedException;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingByIdNotFoundException;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatUtility.assertThatStatusCodeIsOk;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RatingControllerFindByIdIntegrationTest extends RatingControllerIntegrationTest {
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
        Exception foundException = findByIdInvalid(responseEntity.getBody().getId());
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException(foundException);
    }

    @Test
    public void testInvalidFindByIdWithDifferentUser() {
        ResponseEntity<RatingDTO> responseEntity = createValidRating(userStevenWorm,
                VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING);
        login(userFalakNoorahKhoury);
        Exception foundException = findByIdInvalid(responseEntity.getBody().getId());
        assertThatExceptionIsEqualToRatingByIdNotAllowedException(foundException);
    }

    @Test
    public void testInvalidFindByIdWithNotExistentId() {
        ResponseEntity<RatingDTO> responseEntity = createValidRating(userStevenWorm,
                VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING);
        Exception foundException = findByIdInvalid(responseEntity.getBody().getId() + "test");

        assertThatExceptionIsEqualToRatingByIdNotFoundException(foundException);
    }

    private void testValidFindById(RatingDTO input) {
        ResponseEntity<RatingDTO> responseEntity = findByIdSuccessful(input.getId());
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

    private Exception findByIdInvalid(String id) {
        try {
            ratingController.findById(id);
        } catch (Exception e) {
            return e;
        }
        return new Exception();
    }

    private ResponseEntity<RatingDTO> findByIdSuccessful(String id) {
        try {
            return ratingController.findById(id);
        } catch (Exception ignored) {

        }
        return null;
    }
}
