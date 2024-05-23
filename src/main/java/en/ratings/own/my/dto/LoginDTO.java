package en.ratings.own.my.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

import static en.ratings.own.my.constant.AttributeConstants.EXPIRATION_TIME_IN_SECONDS;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginDTO {
    private long expirationTimeInSeconds = EXPIRATION_TIME_IN_SECONDS;
    private ArrayList<String> roles;

    public LoginDTO(ArrayList<String> roles) {
        setRoles(roles);
    }
}
