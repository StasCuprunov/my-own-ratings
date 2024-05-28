import {
    ROUTING_LOGIN,
    ROUTING_LOGOUT,
    ROUTING_RATINGS,
    ROUTING_REGISTRATION,
    ROUTING_SEPARATOR,
    routingById,
    routingCreate
} from "./RoutingConstants";

const ROUTING_GENERAL_ID: string = ROUTING_SEPARATOR + ":id";

export const WEBSITE_ROUTING_INDEX: string = "/";
export const WEBSITE_ROUTING_LOGIN: string = ROUTING_LOGIN;
export const WEBSITE_ROUTING_LOGOUT: string = ROUTING_LOGOUT;
export const WEBSITE_ROUTING_REGISTRATION: string = ROUTING_REGISTRATION;
export const WEBSITE_ROUTING_RATINGS_CREATE: string = routingCreate(ROUTING_RATINGS);
export const WEBSITE_ROUTING_RATINGS_BY_ID: string = routingById(ROUTING_RATINGS, ROUTING_GENERAL_ID);
export const WEBSITE_ROUTING_NOT_FOUND: string = "*";

export const getWebsiteRoutingRatingsById = (id: string) => {
    return routingById(ROUTING_RATINGS, id);
};