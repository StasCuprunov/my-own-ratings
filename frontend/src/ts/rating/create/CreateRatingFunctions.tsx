import {useGet} from "../../interface/useGet";
import {API_ROUTING_RATINGS_CREATE} from "../../constant/routing/APIRoutingConstants";
import {RatingDTO} from "../../dto/RatingDTO";
import {postAxios} from "../../interface/BackendCalls";

export const useCreateRatingInfo = () => {
    return useGet(API_ROUTING_RATINGS_CREATE);
};

export const createRating = async (ratingDTO: RatingDTO) => {
    return await postAxios(API_ROUTING_RATINGS_CREATE, ratingDTO);
};