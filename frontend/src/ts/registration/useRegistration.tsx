import {useGet} from "../interface/useGet";
import {ROUTING_REGISTRATION} from "../interface/APIConstants";

export const useRegistration: any = () => {
    return useGet(ROUTING_REGISTRATION);
};