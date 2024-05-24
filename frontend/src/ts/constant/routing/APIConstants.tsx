import {
    ROUTING_CREATE,
    ROUTING_DELETE,
    ROUTING_EDIT,
    ROUTING_GET,
    ROUTING_LOGIN,
    ROUTING_LOGOUT,
    ROUTING_RATING_ENTRIES,
    ROUTING_RATINGS,
    ROUTING_REGISTRATION,
    ROUTING_USERS
} from "./RoutingConstants";

const ROUTING_ID: string = "/{id}";

const ROUTING_EMAIL: string = "/{email}";

const API_URL: string = "http://localhost:1234";

export const API_ROUTING_GET: string = ROUTING_GET + ROUTING_ID;

export const API_ROUTING_GET_BY_EMAIL: string = "/get-by-email" + ROUTING_EMAIL;

export const API_ROUTING_EDIT: string = ROUTING_EDIT + ROUTING_ID;

export const API_ROUTING_DELETE: string = ROUTING_DELETE + ROUTING_ID;

export const API_ROUTING_REGISTRATION: string = API_URL + ROUTING_REGISTRATION;

export const API_ROUTING_LOGIN: string = API_URL + ROUTING_LOGIN;

export const API_ROUTING_LOGOUT: string =  API_URL + ROUTING_LOGOUT;

export const API_ROUTING_START_PAGE: string = API_URL + "/start-page";

export const API_ROUTING_USERS: string = API_URL + ROUTING_USERS;

export const API_ROUTING_RATINGS: string = API_URL + ROUTING_RATINGS

export const API_ROUTING_RATING_ENTRIES: string = API_URL + ROUTING_RATING_ENTRIES;

export const API_ROUTING_USERS_CREATE: string = API_ROUTING_USERS + ROUTING_CREATE;

export const API_ROUTING_RATINGS_CREATE: string = API_ROUTING_RATINGS + ROUTING_CREATE;