import {useGet} from "../interface/useGet";
import {API_ROUTING_START_PAGE} from "../constant/routing/APIConstants";
import {getWebsiteRoutingRatingsById} from "../constant/routing/WebsiteRoutingConstants";

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

export const getButtonLinkToRating = (id: string) => {
    return {
        type: "button",
        text: "Link",
        to: getWebsiteRoutingRatingsById(id)
    };
};