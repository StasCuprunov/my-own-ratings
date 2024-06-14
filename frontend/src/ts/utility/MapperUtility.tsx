import {RatingDTO} from "../dto/RatingDTO";
import {Rating} from "../model/Rating";

export const mapRatingDTOToRating = (ratingDTO: RatingDTO): Rating => {
    let id: string | undefined = (ratingDTO.id) ? ratingDTO.id : undefined;
    return new Rating(id, ratingDTO.userId, ratingDTO.name, ratingDTO.description);
};