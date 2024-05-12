package en.ratings.own.my.repository.rating;

import en.ratings.own.my.model.rating.RatingEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RatingEntryRepository extends MongoRepository<RatingEntry, String> {
    ArrayList<RatingEntry> findAllByRatingId(String ratingId);

    ArrayList<RatingEntry> findAll();

    <S extends RatingEntry> S save(S ratingEntry);

    void deleteById(String id);
    void deleteAllByRatingId(String ratingId);
}
