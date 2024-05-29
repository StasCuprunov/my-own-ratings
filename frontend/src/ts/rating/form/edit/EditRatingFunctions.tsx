import {getApiRoutingRatingsEditById} from "../../../constant/routing/APIRoutingConstants";
import {useGet} from "../../../interface/useGet";

export const useEditRating = (id: string | undefined) => {
    return useGet(getApiRoutingRatingsEditById(id));
};