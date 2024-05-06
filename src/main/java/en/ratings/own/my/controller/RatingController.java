package en.ratings.own.my.controller;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.service.rating.RatingService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
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
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_RATING;

@RestController
@RequestMapping(ROUTING_RATING)
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping(ROUTING_ID)
    public RatingDTO findById(@PathVariable @NonNull Long id) throws Exception {
        return ratingService.findById(id);
    }

    @PostMapping(ROUTING_CREATE)
    public RatingDTO create(@RequestBody RatingDTO ratingDTO) throws Exception {
        return ratingService.create(ratingDTO);
    }

    @PutMapping(ROUTING_EDIT)
    public RatingDTO update(@RequestBody RatingDTO ratingDTO) throws Exception {
        return ratingService.update(ratingDTO);
    }

    @DeleteMapping(ROUTING_DELETE)
    public void deleteById(@PathVariable @NonNull Long id) throws Exception {
        ratingService.deleteById(id);
    }
}
