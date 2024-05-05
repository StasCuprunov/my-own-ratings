package en.ratings.own.my.service.repository.rating;

import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.repository.rating.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RatingRepositoryService {
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingRepositoryService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Optional<Rating> findById(Long id) {
        return ratingRepository.findById(id);
    }

    public Optional<Rating> findByUserIdAndName(Long userId, String name) {
        return ratingRepository.findByUserIdAndName(userId, name);
    }

    public Optional<ArrayList<Rating>> findAllByUserId(Long userId) {
        return ratingRepository.findAllByUserId(userId);
    }

    public boolean existsByRangeOfValuesId(Long rangeOfValuesId) {
        return ratingRepository.existsByRangeOfValuesId(rangeOfValuesId);
    }

    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    public void deleteById(Long id) {
        ratingRepository.deleteById(id);
    }
}
