package en.ratings.own.my.repository.rating;

import en.ratings.own.my.model.rating.RangeOfValues;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RangeOfValuesRepository extends MongoRepository<RangeOfValues, Long> {
    Optional<RangeOfValues> findById(Long id);

    <S extends RangeOfValues> S save(S rangeOfValues);

    void deleteById(Long id);
}
