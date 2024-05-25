import {useGet} from "../interface/useGet";
import {API_ROUTING_START_PAGE} from "../constant/routing/APIConstants";

export const useStartPage = () => {
    return useGet(API_ROUTING_START_PAGE);
};