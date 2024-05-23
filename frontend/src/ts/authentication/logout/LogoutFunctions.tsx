import {ROUTING_LOGOUT} from "../../interface/APIConstants";
import {useGet} from "../../interface/useGet";

export const useLogout = () => {
    return useGet(ROUTING_LOGOUT);
};