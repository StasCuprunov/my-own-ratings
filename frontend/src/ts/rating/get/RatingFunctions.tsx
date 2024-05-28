import {getApiRoutingRatingsFindById} from "../../constant/routing/APIRoutingConstants";
import {useGet} from "../../interface/useGet";

export const useRating = (id: string | undefined) => {
    return useGet(getApiRoutingRatingsFindById(id));
};