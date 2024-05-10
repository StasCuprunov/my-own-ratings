package en.ratings.own.my;

import en.ratings.own.my.exception.authentication.WrongPasswordLoginException;
import en.ratings.own.my.exception.user.UserByEmailNotFoundException;
import en.ratings.own.my.exception.user.creation.UserCreationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

public class AssertThatUtility {

    public static void assertThatIsNotNull(Object object) {
        assertThat(object).isNotEqualTo(null);
    }

    public static <T> void assertThatStatusCodeIsCreated(ResponseEntity<T> responseEntity) {
        assertThatStatusCodeIsEqualTo(responseEntity, CREATED);
    }

    public static <T> void assertThatStatusCodeIsOk(ResponseEntity<T> responseEntity) {
        assertThatStatusCodeIsEqualTo(responseEntity, OK);
    }

    public static <T> void assertThatStatusCodeIsNoContent(ResponseEntity<T> responseEntity) {
        assertThatStatusCodeIsEqualTo(responseEntity, NO_CONTENT);
    }

    public static void assertThatId(String id) {
        assertThat(id).isNotNull().isNotBlank().isNotEmpty();
    }

    public static <T extends Exception> void assertThatExceptionIsEqualToUserCreationFailedException(T exception) {
        assertThatExceptionIsEqual(exception, UserCreationFailedException.class);
    }

    public static <T extends Exception> void assertThatExceptionIsEqualToUserByEmailNotFoundException(T exception) {
        assertThatExceptionIsEqual(exception, UserByEmailNotFoundException.class);
    }

    public static <T extends Exception> void assertThatExceptionIsEqualToWrongPasswordLoginException(T exception) {
        assertThatExceptionIsEqual(exception, WrongPasswordLoginException.class);
    }

    private static <T extends Exception, S extends Exception> void
    assertThatExceptionIsEqual(T exception, Class<S> expectedExpectionClass) {
        assertThat(exception.getClass()).isEqualTo(expectedExpectionClass);
    }

    private static <T> void assertThatStatusCodeIsEqualTo(ResponseEntity<T> responseEntity, HttpStatus statusCode) {
        assertThat(responseEntity.getStatusCode()).isEqualTo(statusCode);
    }
}
