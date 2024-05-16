package en.ratings.own.my.test.integration.controller;

import en.ratings.own.my.controller.StartPageController;
import en.ratings.own.my.dto.StartPageDTO;
import en.ratings.own.my.dto.UserDTO;
import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.dto.rating.RatingForStartPageDTO;
import en.ratings.own.my.model.User;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.repository.rating.RangeOfValuesRepository;
import en.ratings.own.my.repository.rating.RatingEntryRepository;
import en.ratings.own.my.repository.rating.RatingRepository;
import en.ratings.own.my.test.integration.AbstractIntegrationTest;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static en.ratings.own.my.test.constant.TestConstants.EXPECTED_ZERO;
import static en.ratings.own.my.test.utility.GeneratorUtility.printExceptionMessage;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException;
import static en.ratings.own.my.test.utility.asserts.AssertThatStatusCodeUtility.assertThatStatusCodeIsOk;
import static en.ratings.own.my.test.utility.rating.CreateRatingDTOUtility.creatRatingDTOWithUserId;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.createValidRatingDTODrinksWithNegativeMinimum;
import static en.ratings.own.my.utility.math.MathUtility.isLastIndex;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StartPageControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    protected StartPageController startPageController;

    @Autowired
    protected RatingRepository ratingRepository;

    @Autowired
    protected RangeOfValuesRepository rangeOfValuesRepository;

    @Autowired
    protected RatingEntryRepository ratingEntryRepository;

    @After
    public void clean() {
        deleteAllUserRepository();
        ratingRepository.deleteAll();
        rangeOfValuesRepository.deleteAll();
        ratingEntryRepository.deleteAll();
    }

    @Test
    public void testValidIndexWithRatingsBooksAndDrinks() {
        createValidRatingsInDatabaseForUser(userStevenWorm);
        login(userStevenWorm);
        testValidIndex(userStevenWorm, indexSuccessful());
    }

    @Test
    public void testInvalidIndexWithoutLoggedIn() {
        createValidRatingsInDatabaseForUser(userStevenWorm);
        logout();
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException(indexInvalid());
    }

    private void createValidRatingsInDatabaseForUser(User user) {
        String userId = user.getId();
        ArrayList<RatingDTO> listOfRatingDTOs = new ArrayList<>();
        listOfRatingDTOs.add(creatRatingDTOWithUserId(userId, VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING));
        listOfRatingDTOs.add(creatRatingDTOWithUserId(userId, createValidRatingDTODrinksWithNegativeMinimum()));

        for (RatingDTO ratingDTO: listOfRatingDTOs) {
            RangeOfValues rangeOfValues = saveRangeOfValues(ratingDTO.getRangeOfValues());
            Rating rating = new Rating(userId, rangeOfValues.getId(), ratingDTO.getName(), ratingDTO.getDescription());
            saveRating(rating);
        }
    }

    private void testValidIndex(User loggedInUser, ResponseEntity<StartPageDTO> responseEntity) {
        StartPageDTO result = responseEntity.getBody();
        assertAll(
                () -> assertThatStatusCodeIsOk(responseEntity),
                () -> compareUserWithUserDTO(loggedInUser, result.getUserDTO()),
                () -> compareResultWithDatabase(result)
        );
    }

    private void compareUserWithUserDTO(User user, UserDTO result) {
        assertAll(
                () -> assertThat(result.getId()).isEqualTo(user.getId()),
                () -> assertThat(result.getEmail()).isEqualTo(user.getEmail()),
                () -> assertThat(result.getFirstName()).isEqualTo(user.getFirstName()),
                () -> assertThat(result.getSurname()).isEqualTo(user.getSurname())
        );
    }

    private void compareResultWithDatabase(StartPageDTO result) {
        UserDTO userDTO = result.getUserDTO();
        ArrayList<RatingForStartPageDTO> ratingsForStartPage = result.getRatingDTOs();
        User storedUser = findByIdUserRepository(userDTO.getId()).get();
        assertAll(
                () -> compareUserWithUserDTO(storedUser, userDTO),
                () -> compareRatingForStartPageDTOWithDatabase(userDTO.getId(), ratingsForStartPage)
        );
    }

    private void compareRatingForStartPageDTOWithDatabase(String userId,
                                                          ArrayList<RatingForStartPageDTO> ratingsForStartPage) {
        ArrayList<Rating> storedRatings = findAllByUserIdRatingRepository(userId);
        ArrayList<String> notFoundRatingIdsInStartPageObject = new ArrayList<>();
        int sizeOfFoundRatings = storedRatings.size();
        for (RatingForStartPageDTO ratingForStartPageDTO: ratingsForStartPage) {
            for (int index = 0; index < sizeOfFoundRatings; index++) {
                Rating rating = storedRatings.get(index);
                if (compare(ratingForStartPageDTO, rating)) {
                    break;
                }
                else if (isLastIndex(index, sizeOfFoundRatings)) {
                    notFoundRatingIdsInStartPageObject.add(rating.getId());
                }
            }
        }
        assertThat(notFoundRatingIdsInStartPageObject.size()).isEqualTo(EXPECTED_ZERO);
    }

    private boolean compare(RatingForStartPageDTO ratingForStartPageDTO, Rating rating) {
        return ratingForStartPageDTO.getId().equals(rating.getId()) &&
                ratingForStartPageDTO.getName().equals(rating.getName()) &&
                ratingForStartPageDTO.getDescription().equals(rating.getDescription());
    }

    private ArrayList<Rating> findAllByUserIdRatingRepository(String userId) {
        return ratingRepository.findAllByUserId(userId);
    }

    private Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    private RangeOfValues saveRangeOfValues(RangeOfValues rangeOfValues) {
        return rangeOfValuesRepository.save(rangeOfValues);
    }

    private ResponseEntity<StartPageDTO> indexSuccessful() {
        try {
            return startPageController.index();
        } catch (Exception e) {
            printExceptionMessage(e);
        }
        return null;
    }

    private Exception indexInvalid() {
        try {
            startPageController.index();
        } catch (Exception e) {
            return e;
        }
        return new Exception();
    }
}
