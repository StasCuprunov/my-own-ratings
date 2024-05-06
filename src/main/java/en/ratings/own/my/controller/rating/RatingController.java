package en.ratings.own.my.controller.rating;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.service.rating.RatingService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static en.ratings.own.my.constant.RoutingConstants.ROUTING_CREATE;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_DELETE;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_EDIT;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_ID;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_RATINGS;
import static en.ratings.own.my.utility.ResponseEntityUtility.createCreatedResponseEntity;
import static en.ratings.own.my.utility.ResponseEntityUtility.createNoContentResponseEntity;
import static en.ratings.own.my.utility.ResponseEntityUtility.createOkResponseEntity;

@RestController
@RequestMapping(ROUTING_RATINGS)
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping(ROUTING_ID)
    public ResponseEntity<RatingDTO> findById(@PathVariable @NonNull Long id) throws Exception {
        return createOkResponseEntity(ratingService.findById(id));
    }

    @PostMapping(ROUTING_CREATE)
    public ResponseEntity<RatingDTO> create(@RequestBody RatingDTO ratingDTO) throws Exception {
        return createCreatedResponseEntity(ratingService.create(ratingDTO));
    }

    @PutMapping(ROUTING_EDIT)
    public ResponseEntity<RatingDTO> update(@RequestBody RatingDTO ratingDTO) throws Exception {
        return createOkResponseEntity(ratingService.update(ratingDTO));
    }

    @DeleteMapping(ROUTING_DELETE)
    public ResponseEntity<Object> deleteById(@PathVariable @NonNull Long id) throws Exception {
        ratingService.deleteById(id);
        return createNoContentResponseEntity();
    }
}
