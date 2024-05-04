package en.ratings.own.my.repository.rating;

import en.ratings.own.my.model.rating.RatingEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RatingEntryRepository extends MongoRepository<RatingEntry, Long> {
    Optional<List<RatingEntry>> findAllByRatingId(Long ratingId);

    <S extends RatingEntry> S save(S ratingEntry);

    void deleteById(Long id);
    void deleteAllByRatingId(Long ratingId);
}
