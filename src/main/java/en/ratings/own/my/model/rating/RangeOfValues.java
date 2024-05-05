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
    private Double minimum;

    @NonNull
    private Double maximum;

    @NonNull
    private Double stepWidth;

    public RangeOfValues(Double minimum, Double maximum, Double stepWidth) {
        setMinimum(minimum);
        setMaximum(maximum);
        setStepWidth(stepWidth);
    }

    public boolean equals(RangeOfValues rangeOfValues) {
        boolean isMinimumEqual = (this.minimum.equals(rangeOfValues.getMinimum()));
        boolean isMaximumEqual = (this.maximum.equals(rangeOfValues.getMaximum()));
        boolean isStepWidthEqual = (this.stepWidth.equals(rangeOfValues.getStepWidth()));

        return isMinimumEqual && isMaximumEqual && isStepWidthEqual;
    }
}
