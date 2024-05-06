package en.ratings.own.my.controller;

import en.ratings.own.my.dto.rating.RatingEntryDTO;
import en.ratings.own.my.model.rating.RatingEntry;
import en.ratings.own.my.service.rating.RatingEntryService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static en.ratings.own.my.constant.routing.RoutingConstants.ROUTING_RATING_ENTRY_CREATE;
import static en.ratings.own.my.constant.routing.RoutingConstants.ROUTING_RATING_ENTRY_DELETE;

@RestController
public class RatingEntryController {

    private final RatingEntryService ratingEntryService;
    @Autowired
    public RatingEntryController(RatingEntryService ratingEntryService) {
        this.ratingEntryService = ratingEntryService;
    }

    @PostMapping(ROUTING_RATING_ENTRY_CREATE)
    public RatingEntryDTO create(@RequestBody RatingEntry ratingEntry) throws Exception {
        return ratingEntryService.create(ratingEntry);
    }

    public RatingEntryDTO update(@RequestBody RatingEntry ratingEntry) throws Exception {
        return ratingEntryService.update(ratingEntry);
    }

    @DeleteMapping(ROUTING_RATING_ENTRY_DELETE)
    public void deleteById(@PathVariable @NonNull Long id) {
        ratingEntryService.deleteById(id);
    }
}
