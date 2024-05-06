package en.ratings.own.my.utility;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

public class ResponseEntityUtility {

    public static <T> ResponseEntity<T> createCreatedResponseEntity(T object) {
        return createResponseEntity(object, CREATED);
    }

    public static <T> ResponseEntity<T> createOkResponseEntity(T object) {
        return createResponseEntity(object, OK);
    }

    public static <T> ResponseEntity<T> createNoContentResponseEntity() {
        return createResponseEntity(null, NO_CONTENT);
    }

    private static <T> ResponseEntity<T> createResponseEntity(T object, HttpStatus status) {
        return new ResponseEntity<>(object, new HttpHeaders(), status);
    }
}
