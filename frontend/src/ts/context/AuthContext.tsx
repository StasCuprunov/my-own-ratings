import {createContext, useContext} from "react";

export const AuthContext= createContext({
    authenticated: false,
    setAuthenticated: (auth: boolean) => {}
});

export const useAuth = () => {
    return useContext(AuthContext);
};