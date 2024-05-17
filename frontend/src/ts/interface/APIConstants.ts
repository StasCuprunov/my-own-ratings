const ROUTING_ID: string = "/{id}";

const ROUTING_EMAIL: string = "/{email}";

const API_URL: string = "http://localhost:1234";

export const ROUTING_CREATE: string = "/create";

export const ROUTING_GET: string = "/get" + ROUTING_ID;

export const ROUTING_GET_BY_EMAIL: string = "/get-by-email" + ROUTING_EMAIL;

export const ROUTING_EDIT: string = "/edit" + ROUTING_ID;

export const ROUTING_DELETE: string = "/delete" + ROUTING_ID;

export const ROUTING_REGISTRATION: string = API_URL + "/registration";

export const ROUTING_LOGIN: string = API_URL + "/login";

export const ROUTING_START_PAGE: string = API_URL + "/start-page";

export const ROUTING_USERS: string = API_URL + "/users";

export const ROUTING_RATINGS: string = API_URL + "/ratings";

export const ROUTING_RATING_ENTRIES: string = API_URL + "/rating-entries";