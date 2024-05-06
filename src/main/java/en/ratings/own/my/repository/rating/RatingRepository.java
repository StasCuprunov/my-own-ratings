package en.ratings.own.my.repository.rating;

import en.ratings.own.my.model.rating.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface RatingRepository extends MongoRepository<Rating, Long> {
    Optional<Rating> findById(Long id);

    Optional<Rating> findByUserIdAndName(Long userId, String name);

    ArrayList<Rating> findAllByUserId(Long userId);

    boolean existsByRangeOfValuesId(Long rangeOfValuesId);

    <S extends Rating> S save(S rating);

    void deleteById(Long id);
}
