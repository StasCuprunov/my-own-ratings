package en.ratings.own.my.service.repository.rating;

import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.repository.rating.RangeOfValuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RangeOfValuesRepositoryService {

    private final RangeOfValuesRepository rangeOfValuesRepository;

    @Autowired
    public RangeOfValuesRepositoryService(RangeOfValuesRepository rangeOfValuesRepository) {
        this.rangeOfValuesRepository = rangeOfValuesRepository;
    }

    public Optional<RangeOfValues> findById(Long id) {
        return rangeOfValuesRepository.findById(id);
    }

    public Optional<RangeOfValues> findByMinimumAndMaximumAndStepWidth(RangeOfValues rangeOfValues) {
        return rangeOfValuesRepository.findByMinimumAndMaximumAndStepWidth(rangeOfValues.getMinimum(),
                rangeOfValues.getMaximum(), rangeOfValues.getStepWidth());
    }

    public RangeOfValues save(RangeOfValues rangeOfValues) {
        return rangeOfValuesRepository.save(rangeOfValues);
    }

    public void deleteById(Long id) {
        rangeOfValuesRepository.deleteById(id);
    }
}
