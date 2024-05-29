import {
    API_ROUTING_RATINGS_EDIT,
    getApiRoutingRatingsEditById
} from "../../../constant/routing/APIRoutingConstants";
import {useGet} from "../../../interface/useGet";
import {RatingDTO} from "../../../dto/RatingDTO";
import {putAxios} from "../../../interface/BackendCalls";

export const useEditRating = (id: string | undefined) => {
    return useGet(getApiRoutingRatingsEditById(id));
};

export const editRating = async (ratingDTO: RatingDTO) => {
    return await putAxios(API_ROUTING_RATINGS_EDIT, ratingDTO);
};