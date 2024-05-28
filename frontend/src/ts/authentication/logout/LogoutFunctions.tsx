import {API_ROUTING_LOGOUT} from "../../constant/routing/APIRoutingConstants";
import {useGet} from "../../interface/useGet";

export const useLogout = () => {
    return useGet(API_ROUTING_LOGOUT);
};