import React, {useState} from 'react';
import { Routes, Route, BrowserRouter } from 'react-router-dom';
import {LoadRegistration} from "./registration/LoadRegistration";
import {Login} from "./authentication/login/Login";
import {Logout} from "./authentication/logout/Logout";
import {
    WEBSITE_ROUTING_INDEX,
    WEBSITE_ROUTING_LOGIN,
    WEBSITE_ROUTING_LOGOUT,
    WEBSITE_ROUTING_REGISTRATION
} from "./constant/WebsiteRoutingConstants";
import {StartPage} from "./start-page/StartPage";
import {AuthContext} from "./context/AuthContext";
import {hasRecentlyLoggedIn} from "./utility/CookieUtility";
import {NavBar} from "./component/organism/navbar/NavBar";

export const App = () => {
    const [authenticated, setAuthenticated] = useState(hasRecentlyLoggedIn());
    return (
        <AuthContext.Provider value={{ authenticated, setAuthenticated }}>
            <BrowserRouter>
                <NavBar />
                <Routes>
                    <Route path={WEBSITE_ROUTING_LOGIN} element={<Login />} />)
                    <Route path={WEBSITE_ROUTING_LOGOUT} element={<Logout />}/>
                    <Route path={WEBSITE_ROUTING_INDEX} element={authenticated ? <StartPage/> : <Login/>}/>
                    <Route path={WEBSITE_ROUTING_REGISTRATION} element={<LoadRegistration />} />
                </Routes>
            </BrowserRouter>
        </AuthContext.Provider>
    );
};
