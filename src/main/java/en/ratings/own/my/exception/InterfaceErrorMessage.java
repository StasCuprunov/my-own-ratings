package en.ratings.own.my.exception;

import java.util.ArrayList;

public interface InterfaceErrorMessage {
    String errorMessage(ArrayList<String> keysForException);

    String errorMessageBegin();
}
