package en.ratings.own.my.repository.rating;

import en.ratings.own.my.model.rating.RangeOfValues;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RangeOfValuesRepository extends MongoRepository<RangeOfValues, String> {
    Optional<RangeOfValues> findById(String id);

    Optional<RangeOfValues> findByMinimumAndMaximumAndStepWidth(Double minimum, Double maximum, Double stepWidth);

    <S extends RangeOfValues> S save(S rangeOfValues);

    void deleteById(String id);
}
