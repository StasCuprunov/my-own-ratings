package en.ratings.own.my.test.integration.controller.rating;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.model.rating.RatingEntry;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static en.ratings.own.my.test.integration.utility.asserts.AssertThatUtility.assertThatIdIsDefined;
import static en.ratings.own.my.test.integration.utility.asserts.AssertThatUtility.assertThatStatusCodeIsOk;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.BOOKS_NAME;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.BOOKS_SCIENTIFIC_NAME;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_SCIENTIFIC_WITH_GERMAN_GRADING;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.DRINKS_IN_ASIA_NAME;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        createValidRatingEntryCokeForDrinksWithNegativeMinimum;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RatingControllerUpdateIntegrationTest extends RatingControllerIntegrationTest {

    @Test
    public void testValidUpdateWithName() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING);
        RatingDTO input = responseCreate.getBody();
        input.setName(BOOKS_SCIENTIFIC_NAME);
        testValidUpdate(input, updateSuccessful(input));
    }

    @Test
    public void testValidUpdateWithNameWhichIsAlsoUsedByAnotherUser() {
        createValidRating(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING);
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userFalakNoorahKhoury,
                VALID_RATING_DTO_BOOKS_SCIENTIFIC_WITH_GERMAN_GRADING);
        RatingDTO input = responseCreate.getBody();
        input.setName(BOOKS_NAME);
        testValidUpdate(input, updateSuccessful(input));
    }

    @Test
    public void testValidUpdateWithNameAndDefinedRatingEntriesInInput() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM);
        RatingDTO input = responseCreate.getBody();
        input.setName(DRINKS_IN_ASIA_NAME);
        ArrayList<RatingEntry> ratingEntries = new ArrayList<>();
        ratingEntries.add(createValidRatingEntryCokeForDrinksWithNegativeMinimum(input.getId()));
        input.setRatingEntries(ratingEntries);
        testValidUpdate(input, updateSuccessful(input));
        checkIfRatingEntriesInInputAreCreatedWithUpdate(input);
    }

    @Test
    public void testValidUpdateWithDescription() {

    }

    @Test
    public void testValidUpdateWithEmptyDescription() {

    }

    @Test
    public void testValidUpdateWithNameAndDescription() {

    }

    @Test
    public void testValidUpdateWithMinimumAndDeletingOldRangeOfValues() {

    }

    @Test
    public void testValidUpdateWithMinimumAndWithExistentRatingEntries() {

    }

    @Test
    public void testValidUpdateWithMaximumAndWithExistentRatingEntries() {

    }

    @Test
    public void testValidUpdateWithStepWidthAndWithExistentRatingEntries() {

    }

    @Test
    public void testValidUpdateWithMinimumAndMaximumAndWithExistentRatingEntries() {

    }

    @Test
    public void testValidUpdateWithMinimumAndStepWidthAndWithExistentRatingEntries() {

    }

    @Test
    public void testValidUpdateWithMaximumAndStepWidthAndWithExistentRatingEntries() {

    }

    @Test
    public void testValidUpdateWithMinimumAndMaximumAndStepWidthAndWithExistentRatingEntries() {

    }

    @Test
    public void testValidUpdateWithNameAndMaximumAndWithExistentRatingEntries() {

    }

    @Test
    public void testInvalidUpdateWithoutLoggedIn() {

    }

    @Test
    public void testInvalidUpdateWithWrongId() {

    }

    // Users aren't allowed to change the id
    @Test
    public void testInvalidUpdateWithId() {

    }

    // Users aren't allowed to change the userId
    @Test
    public void testInvalidUpdateWithUserId() {

    }

    @Test
    public void testInvalidUpdateWithEmptyName() {

    }

    @Test
    public void testInvalidUpdateWithAlreadyUsedName() {

    }

    @Test
    public void testInvalidUpdateWithZeroStepWidth() {

    }

    @Test
    public void testInvalidUpdateWithNegativeStepWidth() {

    }

    @Test
    public void testInvalidUpdateWithMinimumEqualsToMaximum() {

    }

    @Test
    public void testInvalidUpdateWithMinimumGreaterThanMaximum() {

    }

    @Test
    public void testInvalidUpdateWithUnavailableMaximum() {

    }

    @Test
    public void testInvalidUpdateWithInconsistentRatingEntryValue() {

    }

    private void testValidUpdate(RatingDTO input, ResponseEntity<RatingDTO> responseEntity) {
        RatingDTO result = responseEntity.getBody();
        assertAll("Test valid update rating:",
                () -> assertThatStatusCodeIsOk(responseEntity),
                () -> compareValidInputWithResultAfterUpdate(input, result),
                () -> compareValidInputWithDatabaseAfterUpdate(input)
        );
    }

    private void compareValidInputWithResultAfterUpdate(RatingDTO input, RatingDTO result) {
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

    private void compareValidInputWithDatabaseAfterUpdate(RatingDTO input) {
        String rangeOfValuesId = compareValidInputWithRatingTable(input);
        compareValidInputWithRangeOfValuesTable(input, rangeOfValuesId);
    }

    private String compareValidInputWithRatingTable(RatingDTO input) {
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

    private void compareValidInputWithRangeOfValuesTable(RatingDTO input, String rangeOfValuesId) {
        RangeOfValues rangeOfValuesInput = input.getRangeOfValues();
        Optional<RangeOfValues> storedRangeOfValuesResult = findByIdRangeOfValuesRepository(rangeOfValuesId);
        RangeOfValues storedRangeOfValues = storedRangeOfValuesResult.get();

        assertAll(
                () -> assertThat(storedRangeOfValuesResult).isPresent(),
                () -> assertThat(storedRangeOfValues.equalsWithoutId(rangeOfValuesInput)).isTrue(),
                () -> compareIfRangeOfValuesExistsExactOnce(input)
        );
    }

    private void checkIfRatingEntriesInInputAreCreatedWithUpdate(RatingDTO input) {
        for (RatingEntry storedRatingEntry: findAllRatingEntryRepository()) {
            for (RatingEntry inputRatingEntry: input.getRatingEntries()) {
                assertThat(inputRatingEntry).isNotEqualTo(storedRatingEntry);
            }
        }
    }

    private ResponseEntity<RatingDTO> updateSuccessful(RatingDTO input) {
        try {
            return ratingController.update(input);
        } catch (Exception ignored) {

        }
        return null;
    }

    private Exception updateInvalid(RatingDTO input) {
        try {
            ratingController.update(input);
        } catch (Exception e) {
            return e;
        }
        return new Exception();
    }

    private Optional<Rating> findByIdRatingRepository(String id) {
        return ratingRepository.findById(id);
    }

    private Optional<RangeOfValues> findByIdRangeOfValuesRepository(String id) {
        return rangeOfValuesRepository.findById(id);
    }

    private ArrayList<RatingEntry> findAllRatingEntryRepository() {
        return ratingEntryRepository.findAll();
    }
}
