export const ROUTING_SEPARATOR: string = "/";
export const ROUTING_GET: string = "/get";
export const ROUTING_CREATE: string = "/create";
export const ROUTING_EDIT: string = "/edit";
export const ROUTING_DELETE: string = "/delete";
export const ROUTING_REGISTRATION: string = "/registration";
export const ROUTING_LOGIN: string = "/login";
export const ROUTING_LOGOUT: string = "/logout";
export const ROUTING_USERS: string = "/users";
export const ROUTING_RATINGS: string = "/ratings";
export const ROUTING_RATING_ENTRIES: string = "/rating-entries";

export const routingCreate = (path: string) => {
    return path + ROUTING_CREATE;
};

export const routingById = (path: string, id: string): string => {
    return path + ROUTING_SEPARATOR + id;
};

export const routingGetById = (path: string, id: string | undefined): string => {
    return routingByVariable(path, ROUTING_GET, id);
};

export const routingEditById = (path: string, id: string | undefined): string => {
    return routingByVariable(path, ROUTING_EDIT, id);
};

export const routingEdit = (path: string): string => {
    return path + ROUTING_EDIT;
};

export const routingDeleteById = (path: string, id: string): string => {
    return routingByVariable(path, ROUTING_DELETE, id);
};

const routingByVariable = (path: string, operator: string, variable: string | undefined): string => {
    return path + operator + ROUTING_SEPARATOR + variable;
};