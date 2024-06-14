package en.ratings.own.my.service.repository.rating;

import en.ratings.own.my.exception.rating.RatingByIdNotFoundException;
import en.ratings.own.my.exception.rating.RatingByUserIdAndNameNotFoundException;
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

    public Rating findById(String id) throws Exception {
        Optional<Rating> ratingResult = ratingRepository.findById(id);
        if (ratingResult.isEmpty()) {
            throw new RatingByIdNotFoundException(id);
        }
        return ratingResult.get();
    }

    public Rating findByUserIdAndName(String userId, String name) throws Exception {
        Optional<Rating> ratingResult = ratingRepository.findByUserIdAndName(userId, name);
        if (ratingResult.isEmpty()) {
            throw new RatingByUserIdAndNameNotFoundException(userId, name);
        }
        return ratingResult.get();
    }

    public ArrayList<Rating> findAllByUserId(String userId) {
        return ratingRepository.findAllByUserId(userId);
    }

    public boolean existsByRangeOfValuesId(String rangeOfValuesId) {
        return ratingRepository.existsByRangeOfValuesId(rangeOfValuesId);
    }

    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    public void deleteById(String id) {
        ratingRepository.deleteById(id);
    }
}
