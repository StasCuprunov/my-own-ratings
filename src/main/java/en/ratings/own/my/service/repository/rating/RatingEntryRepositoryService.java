package en.ratings.own.my.service.repository.rating;

import en.ratings.own.my.model.rating.RatingEntry;
import en.ratings.own.my.repository.rating.RatingEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RatingEntryRepositoryService {

    private final RatingEntryRepository ratingEntryRepository;

    @Autowired
    public RatingEntryRepositoryService(RatingEntryRepository ratingEntryRepository) {
        this.ratingEntryRepository = ratingEntryRepository;
    }

    public Optional<RatingEntry> findById(String id) {
        return ratingEntryRepository.findById(id);
    }

    public ArrayList<RatingEntry> findAllByRatingId(String ratingId) {
        return ratingEntryRepository.findAllByRatingId(ratingId);
    }

    public RatingEntry save(RatingEntry ratingEntry) {
        return ratingEntryRepository.save(ratingEntry);
    }

    public void deleteById(String id) {
        ratingEntryRepository.deleteById(id);
    }

    public void deleteAllByRatingId(String ratingId) {
        ratingEntryRepository.deleteAllByRatingId(ratingId);
    }
}
