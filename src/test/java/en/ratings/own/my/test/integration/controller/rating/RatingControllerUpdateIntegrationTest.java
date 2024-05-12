package en.ratings.own.my.test.integration.controller.rating;

import org.junit.Test;

public class RatingControllerUpdateIntegrationTest extends RatingControllerIntegrationTest {

    // Check if RangeOfValues has been created
    @Test
    public void testValidUpdateWithName() {

    }

    @Test
    public void testValidUpdateWithNameWhichIsAlsoUsedByAnotherUser() {

    }

    @Test
    public void testValidUpdateWithNameAndDefinedRatingEntriesInInput() {

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

}
