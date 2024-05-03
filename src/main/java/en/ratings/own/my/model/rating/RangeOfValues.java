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
public class RangeOfValues {

    @Id
    @Generated
    private Long id;

    @NonNull
    private Long minimum;

    @NonNull
    private Long maximum;

    public RangeOfValues(Long minimum, Long maximum) {
        setMinimum(minimum);
        setMaximum(maximum);
    }
}
