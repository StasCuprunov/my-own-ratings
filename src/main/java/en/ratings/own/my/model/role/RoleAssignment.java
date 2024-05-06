package en.ratings.own.my.model.role;

import org.springframework.data.annotation.Id;
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
public class RoleAssignment {

    @Id
    @Generated
    private String id;

    @NonNull
    private String userId;

    @NonNull
    private String roleId;

    public RoleAssignment(String userId, String roleId) {
        setUserId(userId);
        setRoleId(roleId);
    }
}
