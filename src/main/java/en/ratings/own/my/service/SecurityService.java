package en.ratings.own.my.service;

import en.ratings.own.my.model.User;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.model.rating.RatingEntry;
import en.ratings.own.my.service.authentication.AuthenticationService;
import en.ratings.own.my.service.repository.UserRepositoryService;
import en.ratings.own.my.service.repository.rating.RatingEntryRepositoryService;
import en.ratings.own.my.service.repository.rating.RatingRepositoryService;
import org.springframework.stereotype.Service;

@Service("securityService")
public class SecurityService {
    private final AuthenticationService authenticationService;

    private final UserRepositoryService userRepositoryService;

    private final RatingEntryRepositoryService ratingEntryRepositoryService;

    private final RatingRepositoryService ratingRepositoryService;

    public SecurityService(AuthenticationService authenticationService, UserRepositoryService userRepositoryService,
                           RatingEntryRepositoryService ratingEntryRepositoryService,
                           RatingRepositoryService ratingRepositoryService) {
        this.authenticationService = authenticationService;
        this.userRepositoryService = userRepositoryService;
        this.ratingEntryRepositoryService = ratingEntryRepositoryService;
        this.ratingRepositoryService = ratingRepositoryService;
    }
    public boolean hasPermissionToDeleteByIdRatingEntry(String id) {
        User user = getActualUser();
        if (user == null) {
            return false;
        }
        RatingEntry ratingEntry = getRatingEntry(id);
        if (ratingEntry == null) {
            return false;
        }
        Rating rating = getRating(ratingEntry.getRatingId());
        if (rating == null) {
            return false;
        }
        return rating.getUserId().equals(user.getId());
    }

    private Rating getRating(String id) {
        try {
            return ratingRepositoryService.findById(id);
        } catch (Exception e) {

        }
        return null;
    }

    private RatingEntry getRatingEntry(String id) {
        try {
            return ratingEntryRepositoryService.findById(id);
        } catch (Exception e) {

        }
        return null;
    }

    private User getActualUser() {
        try {
            return userRepositoryService.findByEmail(authenticationService.getAuthentication().getName());
        } catch (Exception e) {

        }
        return null;
    }
}
