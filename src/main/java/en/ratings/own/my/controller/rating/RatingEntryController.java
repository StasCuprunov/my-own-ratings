package en.ratings.own.my.controller.rating;

import en.ratings.own.my.model.rating.RatingEntry;
import en.ratings.own.my.service.rating.RatingEntryService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static en.ratings.own.my.constant.RoutingConstants.ROUTING_CREATE;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_DELETE;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_EDIT;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_RATING_ENTRIES;
import static en.ratings.own.my.utility.ResponseEntityUtility.createCreatedResponseEntity;
import static en.ratings.own.my.utility.ResponseEntityUtility.createNoContentResponseEntity;

@RestController
@RequestMapping(ROUTING_RATING_ENTRIES)
public class RatingEntryController {

    private final RatingEntryService ratingEntryService;
    @Autowired
    public RatingEntryController(RatingEntryService ratingEntryService) {
        this.ratingEntryService = ratingEntryService;
    }

    @PostMapping(ROUTING_CREATE)
    public ResponseEntity<RatingEntry> create(@RequestBody RatingEntry ratingEntry) throws Exception {
        return createCreatedResponseEntity(ratingEntryService.create(ratingEntry));
    }

    @PutMapping(ROUTING_EDIT)
    public ResponseEntity<RatingEntry> update(@RequestBody RatingEntry ratingEntry) throws Exception {
        return createCreatedResponseEntity(ratingEntryService.update(ratingEntry));
    }

    @DeleteMapping(ROUTING_DELETE)
    public ResponseEntity<Object> deleteById(@PathVariable @NonNull Long id) {
        ratingEntryService.deleteById(id);
        return createNoContentResponseEntity();
    }
}
