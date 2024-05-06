package en.ratings.own.my.repository.rating;

import en.ratings.own.my.model.rating.RatingEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RatingEntryRepository extends MongoRepository<RatingEntry, Long> {
    ArrayList<RatingEntry> findAllByRatingId(Long ratingId);

    <S extends RatingEntry> S save(S ratingEntry);

    void deleteById(Long id);
    void deleteAllByRatingId(Long ratingId);
}
