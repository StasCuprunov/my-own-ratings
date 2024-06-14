package en.ratings.own.my.dto.rating.form;

import en.ratings.own.my.dto.rating.RatingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditRatingDTO extends RatingValidationDTO {
    private RatingDTO ratingDTO;

    public EditRatingDTO (String userId, RatingDTO ratingDTO) {
        setUserId(userId);
        setRatingDTO(ratingDTO);
    }
}
