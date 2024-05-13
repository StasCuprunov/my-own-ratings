package en.ratings.own.my.test.integration.controller.rating.entry;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static en.ratings.own.my.test.utility.GeneratorUtility.printExceptionMessage;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingEntryByIdNotFoundException;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingEntryDeleteByIdNotAllowedException;

public class RatingEntryDeleteByIdControllerIntegrationTest extends RatingEntryControllerIntegrationTest {

    @Test
    public void testValidDeleteByIdWithDrinks() {

    }

    @Test
    public void testInvalidDeleteByIdWithoutLoggedIn() {

    }

    @Test
    public void testInvalidDeleteByIdWithUnauthorizedUser() {

    }

    @Test
    public void testInvalidDeleteByIdWithNotExistentId() {

    }

    private void checkIfAuthenticationCredentialsNotFoundException(String id) {
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException(deleteByIdInvalid(id));
    }

    private void checkIfRatingEntryByIdNotFoundException(String id) {
        assertThatExceptionIsEqualToRatingEntryByIdNotFoundException(deleteByIdInvalid(id));
    }

    private void checkIfRatingEntryDeleteByIdNotAllowedException(String id) {
        assertThatExceptionIsEqualToRatingEntryDeleteByIdNotAllowedException(deleteByIdInvalid(id));
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
}
