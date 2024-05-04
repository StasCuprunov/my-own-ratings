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
public class RatingEntry {

    @Id
    @Generated
    private Long id;

    @NonNull
    private Long ratingId;

    // unique within a rating
    @NonNull
    private String name;

    @NonNull
    private Double value;

    public RatingEntry(Long ratingId, String name, Double value) {
        setRatingId(ratingId);
        setName(name);
        setValue(value);
    }
}
