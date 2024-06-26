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

import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_NAME;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShowRatingDTO extends RatingDTO {

    private int maximumLengthOfName = MAX_LENGTH_OF_NAME;

    public ShowRatingDTO(Rating rating, @NonNull RangeOfValues rangeOfValues, ArrayList<RatingEntry> ratingEntries) {
        setId(rating.getId());
        setUserId(rating.getUserId());
        setName(rating.getName());
        setDescription(rating.getDescription());
        setRangeOfValues(rangeOfValues);
        setRatingEntries(ratingEntries);
    }
}
