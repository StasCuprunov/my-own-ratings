package en.ratings.own.my.test.integration.utility.asserts;

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

    private static <T> void assertThatStatusCodeIsEqualTo(ResponseEntity<T> responseEntity, HttpStatus statusCode) {
        assertThat(responseEntity.getStatusCode()).isEqualTo(statusCode);
    }
}
