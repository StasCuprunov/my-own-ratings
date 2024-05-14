package en.ratings.own.my.test.unit;

import en.ratings.own.my.model.rating.RangeOfValues;
import org.junit.Test;

import java.util.ArrayList;

import static en.ratings.own.my.service.rating.RangeOfValuesValidation.cutDoubleAfterMaximumNumberOfDecimalDigits;
import static en.ratings.own.my.service.rating.RangeOfValuesValidation.rangeOfValuesValidation;
import static en.ratings.own.my.test.constant.TestConstants.EXPECTED_ZERO;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.VALID_RANGE_OF_VALUES_AS_AMAZON_RATING;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        createValidRangeOfValuesWithNegativeMinimum;
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
}
