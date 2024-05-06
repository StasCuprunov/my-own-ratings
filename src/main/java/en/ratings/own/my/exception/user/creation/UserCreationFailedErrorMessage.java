package en.ratings.own.my.exception.user.creation;

import en.ratings.own.my.exception.InterfaceErrorMessage;

import java.util.ArrayList;

import static en.ratings.own.my.constant.ExceptionConstants.KEY_EMAIL_SYNTAX;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_EMAIL_ALREADY_EXISTS;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_TOO_SHORT;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_TOO_LONG;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForEmailSyntax;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForEmailAlreadyExists;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForPasswordTooShort;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForPasswordTooLong;
import static en.ratings.own.my.utility.StringUtility.makeText;

public class UserCreationFailedErrorMessage implements InterfaceErrorMessage {
    @Override
    public String errorMessage(ArrayList<String> keysForException) {
        ArrayList<String> errorMessage = new ArrayList<>();
        errorMessage.add(errorMessageBegin());

        for (String key : keysForException) {
            switch (key) {
                case KEY_EMAIL_SYNTAX -> errorMessage.add(errorMessageForEmailSyntax());
                case KEY_EMAIL_ALREADY_EXISTS -> errorMessage.add(errorMessageForEmailAlreadyExists());
                case KEY_PASSWORD_TOO_SHORT -> errorMessage.add(errorMessageForPasswordTooShort());
                case KEY_PASSWORD_TOO_LONG -> errorMessage.add(errorMessageForPasswordTooLong());
            }
        }
        return makeText(errorMessage);
    }

    @Override
    public String errorMessageBegin() {
        return "User creation failed:";
    }
}
