import {useGet} from "../../interface/useGet";
import {API_ROUTING_RATINGS_CREATE} from "../../constant/routing/APIConstants";

export const useCreateRatingInfo = () => {
    return useGet(API_ROUTING_RATINGS_CREATE);
};