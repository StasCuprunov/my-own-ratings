package en.ratings.own.my.service;

import en.ratings.own.my.dto.StartPageDTO;
import en.ratings.own.my.dto.UserDTO;
import en.ratings.own.my.dto.rating.RatingForStartPageDTO;
import en.ratings.own.my.model.User;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.service.authentication.AuthenticationService;
import en.ratings.own.my.service.repository.UserRepositoryService;
import en.ratings.own.my.service.repository.rating.RatingRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static en.ratings.own.my.utility.StringUtility.shortenString;

@Service
public class StartPageService {

    private final UserRepositoryService userRepositoryService;

    private final RatingRepositoryService ratingRepositoryService;

    private final AuthenticationService authenticationService;

    @Autowired
    public StartPageService(UserRepositoryService userRepositoryService,
                            RatingRepositoryService ratingRepositoryService,
                            AuthenticationService authenticationService) {
        this.userRepositoryService = userRepositoryService;
        this.ratingRepositoryService = ratingRepositoryService;
        this.authenticationService = authenticationService;
    }



    public StartPageDTO index() throws Exception {
        String email = authenticationService.getAuthentication().getName();
        User user = userRepositoryService.findByEmail(email);

        ArrayList<RatingForStartPageDTO> ratingDTOs = convertModelsToDTOs(ratingRepositoryService.
                findAllByUserId(user.getId()));
        return new StartPageDTO(new UserDTO(user), ratingDTOs);
    }

    private static ArrayList<RatingForStartPageDTO> convertModelsToDTOs(ArrayList<Rating> ratings) {
        ArrayList<RatingForStartPageDTO> ratingDTOs = new ArrayList<>();
        for (Rating rating: ratings) {
            rating.setDescription(shortenString(rating.getDescription()));
            ratingDTOs.add(new RatingForStartPageDTO(rating));
        }
        return ratingDTOs;
    }
}
