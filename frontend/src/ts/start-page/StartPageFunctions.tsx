import {useGet} from "../interface/useGet";
import {API_ROUTING_START_PAGE} from "../constant/routing/APIConstants";

export const useStartPage = () => {
    return useGet(API_ROUTING_START_PAGE);
};

export const getGreetingName = (firstName: string | null, surname: string | null): string =>{
    let name: string = "User";

    if (firstName && surname) {
        name = firstName + " " + surname;
    }
    else if (firstName) {
        name = firstName;
    }
    else if (surname) {
        name = surname
    }
    return name;
};