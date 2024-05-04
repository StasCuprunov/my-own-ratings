package en.ratings.own.my.repository.rating;

import en.ratings.own.my.model.rating.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends MongoRepository<Rating, Long> {
    Optional<Rating> findById(Long id);

    Optional<List<Rating>> findAllByUserId(Long userId);

    <S extends Rating> S save(S rating);

    void deleteById(Long id);
}
