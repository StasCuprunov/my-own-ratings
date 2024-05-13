package en.ratings.own.my.test.integration.utility.asserts;

import en.ratings.own.my.exception.authentication.WrongPasswordLoginException;
import en.ratings.own.my.exception.rating.RatingByIdNotAllowedException;
import en.ratings.own.my.exception.rating.RatingByIdNotFoundException;
import en.ratings.own.my.exception.rating.RatingDeleteByIdNotAllowedException;
import en.ratings.own.my.exception.rating.creation.RatingCreationFailedException;
import en.ratings.own.my.exception.rating.entry.RatingEntryFailedException;
import en.ratings.own.my.exception.rating.update.RatingUpdateFailedException;
import en.ratings.own.my.exception.rating.update.RatingUpdateNotAllowedException;
import en.ratings.own.my.exception.user.UserByEmailNotFoundException;
import en.ratings.own.my.exception.user.creation.UserCreationFailedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertThatExceptionUtility {

    public static <T extends Exception> void assertThatExceptionIsEqualToUserCreationFailedException(T exception) {
        assertThatExceptionIsEqual(exception, UserCreationFailedException.class);
    }

    public static <T extends Exception> void assertThatExceptionIsEqualToUserByEmailNotFoundException(T exception) {
        assertThatExceptionIsEqual(exception, UserByEmailNotFoundException.class);
    }

    public static <T extends Exception> void assertThatExceptionIsEqualToWrongPasswordLoginException(T exception) {
        assertThatExceptionIsEqual(exception, WrongPasswordLoginException.class);
    }

    public static <T extends Exception> void assertThatExceptionIsEqualToRatingCreationFailedException(T exception) {
        assertThatExceptionIsEqual(exception, RatingCreationFailedException.class);
    }

    public static <T extends Exception> void
    assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException(T exception) {
        assertThatExceptionIsEqual(exception, AuthenticationCredentialsNotFoundException.class);
    }

    public static <T extends Exception> void assertThatExceptionIsEqualToRatingByIdNotAllowedException(T exception) {
        assertThatExceptionIsEqual(exception, RatingByIdNotAllowedException.class);
    }

    public static <T extends Exception> void assertThatExceptionIsEqualToRatingByIdNotFoundException(T exception) {
        assertThatExceptionIsEqual(exception, RatingByIdNotFoundException.class);
    }

    public static <T extends Exception> void
    assertThatExceptionIsEqualToRatingDeleteByIdNotAllowedException(T exception) {
        assertThatExceptionIsEqual(exception, RatingDeleteByIdNotAllowedException.class);
    }

    public static <T extends Exception> void assertThatExceptionIsEqualToRatingUpdateFailedException(T exception) {
        assertThatExceptionIsEqual(exception, RatingUpdateFailedException.class);
    }

    public static <T extends Exception> void assertThatExceptionIsEqualToRatingUpdateNotAllowedException(T exception){
        assertThatExceptionIsEqual(exception, RatingUpdateNotAllowedException.class);
    }

    public static <T extends Exception> void assertThatExceptionIsEqualToRatingEntryFailedException(T exception) {
        assertThatExceptionIsEqual(exception, RatingEntryFailedException.class);
    }

    private static <T extends Exception, S extends Exception> void
    assertThatExceptionIsEqual(T exception, Class<S> expectedExpectionClass) {
        assertThat(exception.getClass()).isEqualTo(expectedExpectionClass);
    }
}
