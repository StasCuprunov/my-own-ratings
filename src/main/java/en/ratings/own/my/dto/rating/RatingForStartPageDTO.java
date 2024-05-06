package en.ratings.own.my.dto.rating;

import en.ratings.own.my.model.rating.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RatingForStartPageDTO {

    private String id;

    private String name;

    private String description;

    public RatingForStartPageDTO(Rating rating) {
        this.id = rating.getId();
        this.name = rating.getName();
        this.description = rating.getDescription();
    }
}
