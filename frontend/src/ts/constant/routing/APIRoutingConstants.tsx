import {
    ROUTING_LOGIN,
    ROUTING_LOGOUT,
    ROUTING_RATING_ENTRIES,
    ROUTING_RATINGS,
    ROUTING_REGISTRATION,
    ROUTING_USERS, routingCreate, routingGetById
} from "./RoutingConstants";

const API_URL: string = "http://localhost:1234";

const getRoutingPath = (path: string): string => {
    return API_URL + path;
};

export const API_ROUTING_REGISTRATION: string = getRoutingPath(ROUTING_REGISTRATION);

export const API_ROUTING_LOGIN: string = getRoutingPath(ROUTING_LOGIN);

export const API_ROUTING_LOGOUT: string = getRoutingPath(ROUTING_LOGOUT);

export const API_ROUTING_START_PAGE: string = getRoutingPath("/start-page");

export const API_ROUTING_USERS: string = getRoutingPath(ROUTING_USERS);

export const API_ROUTING_RATINGS: string = getRoutingPath(ROUTING_RATINGS);

export const API_ROUTING_RATING_ENTRIES: string = getRoutingPath(ROUTING_RATING_ENTRIES);

export const API_ROUTING_USERS_CREATE: string = routingCreate(API_ROUTING_USERS);

export const API_ROUTING_RATINGS_CREATE: string = routingCreate(API_ROUTING_RATINGS);

export const getApiRoutingRatingsFindById = (id: string | undefined) => {
    return routingGetById(API_ROUTING_RATINGS, id);
};