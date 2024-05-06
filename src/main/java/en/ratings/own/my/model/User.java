package en.ratings.own.my.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Generated
    private String id;

    @Indexed(unique = true)
    @NonNull
    private String email;

    @NonNull
    private String firstName;

    @NonNull
    private String surname;

    @NonNull
    private String password;

    public User (String email, String firstName, String surname, String password) {
        setEmail(email);
        setFirstName(firstName);
        setSurname(surname);
        setPassword(password);
    }

}
