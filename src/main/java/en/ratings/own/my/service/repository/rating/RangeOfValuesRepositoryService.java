package en.ratings.own.my.service.repository.rating;

import en.ratings.own.my.exception.rating.range.of.values.RangeOfValuesByIdNotFoundException;
import en.ratings.own.my.exception.rating.range.of.values.RangeOfValuesByMinimumAndMaximumAndStepWidthNotFoundException;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.repository.rating.RangeOfValuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static en.ratings.own.my.utility.math.DecimalFormatUtility.cutDoubleAfterMaximumNumberOfDecimalDigits;

@Service
public class RangeOfValuesRepositoryService {

    private final RangeOfValuesRepository rangeOfValuesRepository;

    @Autowired
    public RangeOfValuesRepositoryService(RangeOfValuesRepository rangeOfValuesRepository) {
        this.rangeOfValuesRepository = rangeOfValuesRepository;
    }

    public RangeOfValues findById(String id) throws Exception {
        Optional<RangeOfValues> rangeOfValuesResult = rangeOfValuesRepository.findById(id);
        if (rangeOfValuesResult.isEmpty()) {
            throw new RangeOfValuesByIdNotFoundException(id);
        }
        return rangeOfValuesResult.get();
    }

    public RangeOfValues findByMinimumAndMaximumAndStepWidth(RangeOfValues rangeOfValues) throws Exception {
        Optional<RangeOfValues> rangeOfValuesResult = rangeOfValuesRepository.
                findByMinimumAndMaximumAndStepWidth(rangeOfValues.getMinimum(), rangeOfValues.getMaximum(),
                        rangeOfValues.getStepWidth());
        if (rangeOfValuesResult.isEmpty()) {
            throw new RangeOfValuesByMinimumAndMaximumAndStepWidthNotFoundException(rangeOfValues);
        }
        return rangeOfValuesResult.get();
    }

    public RangeOfValues save(RangeOfValues rangeOfValues) {
        Double minimum = cutDoubleAfterMaximumNumberOfDecimalDigits(rangeOfValues.getMinimum());
        Double maximum = cutDoubleAfterMaximumNumberOfDecimalDigits(rangeOfValues.getMaximum());
        Double stepWidth = cutDoubleAfterMaximumNumberOfDecimalDigits(rangeOfValues.getStepWidth());
        return rangeOfValuesRepository.save(new RangeOfValues(minimum, maximum, stepWidth));
    }

    public void deleteById(String id) {
        rangeOfValuesRepository.deleteById(id);
    }
}
