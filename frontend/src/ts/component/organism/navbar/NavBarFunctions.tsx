import {WEBSITE_ROUTING_LOGOUT} from "../../../constant/WebsiteRoutingConstants";

export const getLogoutObject = ()=> {
    return {
        to: WEBSITE_ROUTING_LOGOUT,
        text: "Logout"
    };
};