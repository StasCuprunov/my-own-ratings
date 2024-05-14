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
    private String id;

    @NonNull
    private String userId;

    @NonNull
    private String rangeOfValuesId;

    // unique for every user
    @NonNull
    private String name;

    private String description;

    public Rating(String userId, String rangeOfValuesId, String name, String description) {
        setUserId(userId);
        setRangeOfValuesId(rangeOfValuesId);
        setName(name);
        setDescription(description);
    }

    public boolean equals(Rating rating) {
        return id.equals(rating.getId()) && userId.equals(rating.getUserId()) &&
                rangeOfValuesId.equals(rating.getRangeOfValuesId()) && name.equals(rating.getName()) &&
                description.equals(rating.getDescription());
    }
}
