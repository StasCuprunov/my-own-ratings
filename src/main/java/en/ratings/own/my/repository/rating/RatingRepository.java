package en.ratings.own.my.repository.rating;

import en.ratings.own.my.model.rating.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
    Optional<Rating> findById(String id);

    Optional<Rating> findByUserIdAndName(String userId, String name);

    ArrayList<Rating> findAllByUserId(String userId);

    ArrayList<Rating> findAll();

    boolean existsByRangeOfValuesId(String rangeOfValuesId);

    <S extends Rating> S save(S rating);

    void deleteById(String id);
}
