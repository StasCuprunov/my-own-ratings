package en.ratings.own.my.test.unit;

import en.ratings.own.my.model.rating.RangeOfValues;
import org.junit.Test;

import java.util.ArrayList;

import static en.ratings.own.my.constant.ExceptionConstants.KEY_MAXIMUM_HAS_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MAXIMUM_IS_TOO_BIG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MINIMUM_HAS_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MINIMUM_IS_TOO_BIG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MINIMUM_IS_TOO_SMALL;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RANGE_OF_VALUES_IS_NOT_CONSISTENT;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_STEP_WIDTH_HAS_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_STEP_WIDTH_IS_TOO_SMALL;
import static en.ratings.own.my.service.rating.RangeOfValuesValidation.rangeOfValuesValidation;
import static en.ratings.own.my.test.constant.TestConstants.EXPECTED_ZERO;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_MAXIMUM_AND_STEP_WIDTH_HAVE_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_MINIMUM_AND_STEP_WIDTH_HAVE_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_MINIMUM_GREATER_THAN_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_NEGATIVE_STEP_WIDTH;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_STEP_WIDTH_HAS_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_TOO_BIG_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_TOO_SMALL_MINIMUM;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_UNAVAILABLE_MAXIMUM_NUMBER_ONE;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_UNAVAILABLE_MAXIMUM_NUMBER_TWO;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_ZERO_STEP_WIDTH;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.VALID_RANGE_OF_VALUES_AS_AMAZON_RATING;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_MINIMUM_EQUALS_TO_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        createValidRangeOfValuesWithNegativeMinimum;
import static en.ratings.own.my.utility.math.DecimalFormatUtility.cutDoubleAfterMaximumNumberOfDecimalDigits;
import static en.ratings.own.my.utility.math.MathUtility.isLastIndex;
import static en.ratings.own.my.utility.StringUtility.print;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RangeOfValuesValidationUnitTest extends AbstractUnitTest {

    @Test
    public void testValidRangeOfValuesWithGermanGrading() {
        shouldBeValidRangeOfValues(VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING);
    }

    @Test
    public void testValidRangeOfValuesWithAmazonRating() {
        shouldBeValidRangeOfValues(VALID_RANGE_OF_VALUES_AS_AMAZON_RATING);
    }

    @Test
    public void testValidRangeOfValuesWithNegativeMinimumAndMaximum() {
        shouldBeValidRangeOfValues(VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM);
    }

    @Test
    public void testValidRangeOfValuesWithNegativeMinimum() {
        shouldBeValidRangeOfValues(createValidRangeOfValuesWithNegativeMinimum());
    }
    @Test
    public void testInvalidRangeOfValuesWithTooBigMinimum() {
        shouldBeInvalidBecauseTooBigMinimum(INVALID_RANGE_OF_VALUES_WITH_MINIMUM_GREATER_THAN_MAXIMUM);
    }

    @Test
    public void testInvalidRangeOfValuesWithTooSmallMinimum() {
        shouldBeInvalidBecauseTooSmallMinimum(INVALID_RANGE_OF_VALUES_WITH_TOO_SMALL_MINIMUM);
    }

    @Test
    public void testInvalidRangeOfValuesWithMinimumAndStepWidthHaveTooManyDecimalDigits() {
        shouldBeInvalidBecauseMinimumAndStepWidthHaveTooManyDecimalDigits(
                INVALID_RANGE_OF_VALUES_WITH_MINIMUM_AND_STEP_WIDTH_HAVE_TOO_MANY_DECIMAL_DIGITS);
    }

    @Test
    public void testInvalidRangeOfValuesWithTooBigMaximum() {
        shouldBeInvalidBecauseTooBigMaximum(INVALID_RANGE_OF_VALUES_WITH_TOO_BIG_MAXIMUM);
    }

    @Test
    public void testInvalidRangeOFValuesWithMaximumHasTooManyDecimalDigits() {
        shouldBeInvalidBecauseMaximumAndStepWidthHaveTooManyDecimalDigits(
                INVALID_RANGE_OF_VALUES_WITH_MAXIMUM_AND_STEP_WIDTH_HAVE_TOO_MANY_DECIMAL_DIGITS);
    }

    @Test
    public void testInvalidRangeOfValuesWithNegativeStepWidth() {
        shouldBeInvalidBecauseTooSmallStepWidth(INVALID_RANGE_OF_VALUES_WITH_NEGATIVE_STEP_WIDTH);
    }

    @Test
    public void testInvalidRangeOfValuesWithZeroStepWidth() {
        shouldBeInvalidBecauseTooSmallStepWidth(INVALID_RANGE_OF_VALUES_WITH_ZERO_STEP_WIDTH);
    }

    @Test
    public void tesInvalidRangeOfValuesWithStepWidthHasTooManyDecimalDigits() {
        shouldBeInvalidBecauseStepWidthHasTooManyDecimalDigits(
                INVALID_RANGE_OF_VALUES_WITH_STEP_WIDTH_HAS_TOO_MANY_DECIMAL_DIGITS);
    }

    @Test
    public void testInvalidRangeOfValuesWithMinimumEqualsToMaximum() {
        shouldBeInvalidBecauseTooBigMinimum(INVALID_RANGE_OF_VALUES_WITH_MINIMUM_EQUALS_TO_MAXIMUM);
    }

    @Test
    public void testInvalidRangeOfValuesWithMinimumGreaterThanMaximum() {
        shouldBeInvalidBecauseTooBigMinimum(INVALID_RANGE_OF_VALUES_WITH_MINIMUM_GREATER_THAN_MAXIMUM);
    }

    @Test
    public void testInvalidRangeOfValuesWithUnavailableMaximumNumberOne() {
        shouldBeInvalidBecauseInconsistent(INVALID_RANGE_OF_VALUES_WITH_UNAVAILABLE_MAXIMUM_NUMBER_ONE);
    }

    @Test
    public void testInvalidRangeOfValueWithUnavailableMaximumNumberTwo() {
        shouldBeInvalidBecauseInconsistent(INVALID_RANGE_OF_VALUES_WITH_UNAVAILABLE_MAXIMUM_NUMBER_TWO);
    }

    @Test
    public void testCutDoubleAfterMaximumNumberOfDecimalDigits() {
        Double expectedValue = 3.149;
        Double cutNumberOne = cutDoubleAfterMaximumNumberOfDecimalDigits(3.1499);
        Double cutNumberTwo = cutDoubleAfterMaximumNumberOfDecimalDigits(3.14999);
        Double cutNumberThree = cutDoubleAfterMaximumNumberOfDecimalDigits(3.149999);
        assertAll(
                () -> assertThat(cutNumberOne).isEqualTo(expectedValue),
                () -> assertThat(cutNumberTwo).isEqualTo(expectedValue),
                () -> assertThat(cutNumberThree).isEqualTo(expectedValue)
        );
    }

    private void shouldBeValidRangeOfValues(RangeOfValues rangeOfValues) {
        ArrayList<String> keysForException = rangeOfValuesValidation(rangeOfValues);
        int size = keysForException.size();
        if (size != EXPECTED_ZERO) {
            print(keysForException);
        }
        assertThat(size).isEqualTo(EXPECTED_ZERO);
    }

    private void shouldBeInvalidBecauseTooBigMinimum(RangeOfValues rangeOfValues) {
        shouldBeInvalidBecauseSingleReason(rangeOfValues, KEY_MINIMUM_IS_TOO_BIG);
    }
    private void shouldBeInvalidBecauseTooSmallMinimum(RangeOfValues rangeOfValues) {
        shouldBeInvalidBecauseSingleReason(rangeOfValues, KEY_MINIMUM_IS_TOO_SMALL);
    }

    private void shouldBeInvalidBecauseTooBigMaximum(RangeOfValues rangeOfValues) {
        shouldBeInvalidBecauseSingleReason(rangeOfValues, KEY_MAXIMUM_IS_TOO_BIG);
    }

    private void shouldBeInvalidBecauseMaximumAndStepWidthHaveTooManyDecimalDigits(RangeOfValues rangeOfValues) {
        ArrayList<String> keys = new ArrayList<>();
        keys.add(KEY_MAXIMUM_HAS_TOO_MANY_DECIMAL_DIGITS);
        keys.add(KEY_STEP_WIDTH_HAS_TOO_MANY_DECIMAL_DIGITS);
        shouldBeInvalidRangeOfValues(rangeOfValues, keys);
    }

    private void shouldBeInvalidBecauseTooSmallStepWidth(RangeOfValues rangeOfValues) {
        shouldBeInvalidBecauseSingleReason(rangeOfValues, KEY_STEP_WIDTH_IS_TOO_SMALL);
    }

    private void shouldBeInvalidBecauseStepWidthHasTooManyDecimalDigits(RangeOfValues rangeOfValues) {
        shouldBeInvalidBecauseSingleReason(rangeOfValues, KEY_STEP_WIDTH_HAS_TOO_MANY_DECIMAL_DIGITS);
    }

    private void shouldBeInvalidBecauseInconsistent(RangeOfValues rangeOfValues) {
        shouldBeInvalidBecauseSingleReason(rangeOfValues, KEY_RANGE_OF_VALUES_IS_NOT_CONSISTENT);
    }

    private void shouldBeInvalidBecauseMinimumAndStepWidthHaveTooManyDecimalDigits(RangeOfValues rangeOfValues) {
        ArrayList<String> keys = new ArrayList<>();
        keys.add(KEY_MINIMUM_HAS_TOO_MANY_DECIMAL_DIGITS);
        keys.add(KEY_STEP_WIDTH_HAS_TOO_MANY_DECIMAL_DIGITS);
        shouldBeInvalidRangeOfValues(rangeOfValues, keys);
    }

    private void shouldBeInvalidBecauseSingleReason(RangeOfValues rangeOfValues, String key) {
        shouldBeInvalidRangeOfValues(rangeOfValues, createListWithSingleString(key));
    }

    private ArrayList<String> createListWithSingleString(String string) {
        ArrayList<String> list = new ArrayList<>();
        list.add(string);
        return list;
    }

    private void shouldBeInvalidRangeOfValues(RangeOfValues rangeOfValues, ArrayList<String> expectedKeys) {
        ArrayList<String> foundedKeys = rangeOfValuesValidation(rangeOfValues);
        int sizeOfFoundedKeys = foundedKeys.size();
        ArrayList<String> notFoundKeys = new ArrayList<>();
        for (String expectedKey: expectedKeys) {
            for (int index = 0; index < sizeOfFoundedKeys; index++) {
                String foundedKey = foundedKeys.get(index);
                if (foundedKey.equals(expectedKey)) {
                    break;
                }
                else if (isLastIndex(index, sizeOfFoundedKeys)) {
                    notFoundKeys.add(expectedKey);
                }
            }
        }
        if (!notFoundKeys.isEmpty()) {
            print(notFoundKeys);
        }
        assertAll(
                () -> assertThat(sizeOfFoundedKeys).isEqualTo(expectedKeys.size()),
                () -> assertThat(notFoundKeys.isEmpty()).isTrue()
        );
    }
}
