import {API_ROUTING_LOGOUT} from "../../constant/routing/APIConstants";
import {useGet} from "../../interface/useGet";

export const useLogout = () => {
    return useGet(API_ROUTING_LOGOUT);
};