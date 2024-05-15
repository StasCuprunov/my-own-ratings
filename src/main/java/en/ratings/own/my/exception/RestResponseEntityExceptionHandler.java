package en.ratings.own.my.exception;

import en.ratings.own.my.exception.authentication.EmailNotFoundInTokenException;
import en.ratings.own.my.exception.authentication.InvalidTokenException;
import en.ratings.own.my.exception.authentication.WrongPasswordLoginException;
import en.ratings.own.my.exception.rating.RatingByIdNotAllowedException;
import en.ratings.own.my.exception.rating.RatingByIdNotFoundException;
import en.ratings.own.my.exception.rating.RatingByUserIdAndNameNotFoundException;
import en.ratings.own.my.exception.rating.creation.RatingCreationFailedException;
import en.ratings.own.my.exception.rating.entry.RatingEntryByIdNotFoundException;
import en.ratings.own.my.exception.rating.entry.RatingEntryFailedException;
import en.ratings.own.my.exception.rating.range.of.values.RangeOfValuesByIdNotFoundException;
import en.ratings.own.my.exception.rating.range.of.values.RangeOfValuesByMinimumAndMaximumAndStepWidthNotFoundException;
import en.ratings.own.my.exception.rating.update.RatingUpdateFailedException;
import en.ratings.own.my.exception.rating.update.RatingUpdateNotAllowedException;
import en.ratings.own.my.exception.role.RoleByIdNotFoundException;
import en.ratings.own.my.exception.user.UserByEmailNotFoundException;
import en.ratings.own.my.exception.user.creation.UserCreationFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserByEmailNotFoundException.class, RoleByNameNotFoundException.class,
            RatingByIdNotFoundException.class, RatingEntryByIdNotFoundException.class,
            RoleByIdNotFoundException.class, RatingByUserIdAndNameNotFoundException.class,
            RangeOfValuesByIdNotFoundException.class,
            RangeOfValuesByMinimumAndMaximumAndStepWidthNotFoundException.class })
    protected ResponseEntity<String> handleNotFound(Exception exception) {
        return createResponseEntity(exception, NOT_FOUND);
    }

    @ExceptionHandler(value = {UserCreationFailedException.class, RatingCreationFailedException.class,
            RatingUpdateFailedException.class, RatingEntryFailedException.class, WrongPasswordLoginException.class,
            EmailNotFoundInTokenException.class, InvalidTokenException.class})
    protected ResponseEntity<String> handleBadRequest(Exception exception) {
        return createResponseEntity(exception, BAD_REQUEST);
    }

    @ExceptionHandler(value = {RatingByIdNotAllowedException.class, RatingUpdateNotAllowedException.class})
    protected ResponseEntity<String> handleUnauthorized(Exception exception) {
        return createResponseEntity(exception, UNAUTHORIZED);
    }

    private ResponseEntity<String> createResponseEntity(Exception exception, HttpStatus status) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), status);
    }
}
