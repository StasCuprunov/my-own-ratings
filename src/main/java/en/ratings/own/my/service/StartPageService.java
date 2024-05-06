package en.ratings.own.my.service;

import en.ratings.own.my.dto.StartPageDTO;
import en.ratings.own.my.dto.UserDTO;
import en.ratings.own.my.dto.rating.RatingForStartPageDTO;
import en.ratings.own.my.exception.user.UserNotFoundByEmailException;
import en.ratings.own.my.model.User;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.service.repository.UserRepositoryService;
import en.ratings.own.my.service.repository.rating.RatingRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StartPageService {

    private final UserRepositoryService userRepositoryService;

    private final RatingRepositoryService ratingRepositoryService;

    @Autowired
    public StartPageService(UserRepositoryService userRepositoryService,
                            RatingRepositoryService ratingRepositoryService) {
        this.userRepositoryService = userRepositoryService;
        this.ratingRepositoryService = ratingRepositoryService;
    }

    public StartPageDTO getByUserEmail(String email) throws Exception {
        Optional<User> userResult = userRepositoryService.findByEmail(email);

        if (userResult.isEmpty()) {
            throw new UserNotFoundByEmailException(email);
        }

        User user = userResult.get();
        ArrayList<RatingForStartPageDTO> ratingDTOs = convertModelsToDTOs(ratingRepositoryService.
                findAllByUserId(user.getId()));

        return new StartPageDTO(new UserDTO(user), ratingDTOs);
    }

    private static ArrayList<RatingForStartPageDTO> convertModelsToDTOs(ArrayList<Rating> ratings) {
        ArrayList<RatingForStartPageDTO> ratingDTOs = new ArrayList<>();
        for (Rating rating: ratings) {
            ratingDTOs.add(new RatingForStartPageDTO(rating));
        }
        return ratingDTOs;
    }
}
