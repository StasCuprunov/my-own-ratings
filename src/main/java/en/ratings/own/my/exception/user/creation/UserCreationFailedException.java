package en.ratings.own.my.exception.user.creation;

import java.util.ArrayList;

public class UserCreationFailedException extends Exception {
    public UserCreationFailedException(ArrayList<String> keysForException) {
        super(new UserCreationFailedErrorMessage().errorMessage(keysForException));
    }
}
