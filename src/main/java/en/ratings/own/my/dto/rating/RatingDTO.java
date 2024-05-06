package en.ratings.own.my.dto.rating;

import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.model.rating.RatingEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RatingDTO {

    private String id;

    @NonNull
    private String userId;

    @NonNull
    private String name;

    private String description;

    @NonNull
    private RangeOfValues rangeOfValues;

    private ArrayList<RatingEntry> ratingEntries;

    public RatingDTO(Rating rating, @NonNull RangeOfValues rangeOfValues, ArrayList<RatingEntry> ratingEntries) {
        this.id = rating.getId();
        this.userId = rating.getUserId();
        this.name = rating.getName();
        this.description = rating.getDescription();
        this.rangeOfValues = rangeOfValues;
        this.ratingEntries = ratingEntries;
    }

    public RatingDTO(Rating rating, @NonNull RangeOfValues rangeOfValues) {
        new RatingDTO(rating, rangeOfValues, null);
    }
}
