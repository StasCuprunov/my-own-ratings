package en.ratings.own.my.dto.rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RatingEntryDTO {
    private Long id;

    private Long ratingId;

    private String name;

    private Double value;
}
