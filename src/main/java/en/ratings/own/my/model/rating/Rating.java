package en.ratings.own.my.model.rating;

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
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    @Generated
    private Long id;

    @NonNull
    private Long userId;

    @NonNull
    private Long rangeOfValuesId;

    // unique for every user
    @NonNull
    private String name;

    private String description;

    public Rating(Long userId, Long rangeOfValuesId, String name, String description) {
        setUserId(userId);
        setRangeOfValuesId(rangeOfValuesId);
        setName(name);
        setDescription(description);
    }
}
