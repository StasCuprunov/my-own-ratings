package en.ratings.own.my.test.utility.asserts;

import en.ratings.own.my.exception.authentication.WrongPasswordLoginException;
import en.ratings.own.my.exception.rating.creation.RatingCreationFailedException;
import en.ratings.own.my.exception.rating.entry.RatingEntryByIdNotFoundException;
import en.ratings.own.my.exception.rating.entry.RatingEntryFailedException;
import en.ratings.own.my.exception.rating.update.RatingUpdateFailedException;
import en.ratings.own.my.exception.user.UserByEmailNotFoundException;
import en.ratings.own.my.exception.user.creation.UserCreationFailedException;
import org.springframework.security.access.AccessDeniedException;
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

    public static <T extends Exception> void assertThatExceptionIsEqualToRatingUpdateFailedException(T exception) {
        assertThatExceptionIsEqual(exception, RatingUpdateFailedException.class);
    }

    public static <T extends Exception> void assertThatExceptionIsEqualToRatingEntryFailedException(T exception) {
        assertThatExceptionIsEqual(exception, RatingEntryFailedException.class);
    }

    public static <T extends Exception> void assertThatExceptionIsEqualToRatingEntryByIdNotFoundException(T exception) {
        assertThatExceptionIsEqual(exception, RatingEntryByIdNotFoundException.class);
    }

    public static <T extends Exception> void
    assertThatExceptionIsEqualToAccessDeniedException(T exception) {
        assertThatExceptionIsEqual(exception, AccessDeniedException.class);
    }

    private static <T extends Exception, S extends Exception> void
    assertThatExceptionIsEqual(T exception, Class<S> expectedExpectionClass) {
        assertThat(exception.getClass()).isEqualTo(expectedExpectionClass);
    }
}
