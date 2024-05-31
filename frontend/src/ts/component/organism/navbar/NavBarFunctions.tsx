import {
    WEBSITE_ROUTING_INDEX,
    WEBSITE_ROUTING_LOGOUT,
    WEBSITE_ROUTING_RATINGS_CREATE
} from "../../../constant/routing/WebsiteRoutingConstants";
export const getLogoutProps = ()=> {
    return {
        to: WEBSITE_ROUTING_LOGOUT,
        text: "Logout"
    };
};

export const getStartPageProps = () => {
    return {
        to: WEBSITE_ROUTING_INDEX,
        text: "Start page"
    };
};

export const getCreateRatingProps = () => {
    return {
        to: WEBSITE_ROUTING_RATINGS_CREATE,
        text: "Create rating"
    };
};
