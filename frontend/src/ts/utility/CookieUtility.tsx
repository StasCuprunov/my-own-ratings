import Cookies from "universal-cookie";

export const hasRecentlyLoggedIn = (): boolean => {
    return isCookieDefined(COOKIE_HAS_LOGGED_IN_RECENTLY_AS_ROLE);
};

export const setCookieHasLoggedInRecentlyAsRole = (roles: string[], maxAge: number): void => {
    cookies.set(COOKIE_HAS_LOGGED_IN_RECENTLY_AS_ROLE, roles.toString(),
        { maxAge: maxAge, sameSite: SAME_SITE_STRICT});
};

export const deleteCookieHasLoggedInRecentlyAsRole = (): void => {
    deleteCookie(COOKIE_HAS_LOGGED_IN_RECENTLY_AS_ROLE);
};

const COOKIE_HAS_LOGGED_IN_RECENTLY_AS_ROLE: string = "hasLoggedInRecentlyAsRole";

const SAME_SITE_STRICT: "strict" = "strict";

const cookies = new Cookies();

const isCookieDefined = (cookieName: string): boolean => {
    return cookies.get(cookieName) !== undefined;
};

const deleteCookie = (cookieName: string): void => {
    cookies.remove(cookieName);
};