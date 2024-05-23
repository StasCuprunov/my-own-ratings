import React, {useState} from 'react';
import { Routes, Route, BrowserRouter } from 'react-router-dom';
import {LoadRegistration} from "./registration/LoadRegistration";
import {Login} from "./authentication/login/Login";
import {Logout} from "./authentication/logout/Logout";
import {
    WEBSITE_ROUTING_INDEX,
    WEBSITE_ROUTING_LOGIN,
    WEBSITE_ROUTING_LOGOUT, WEBSITE_ROUTING_NOT_FOUND, WEBSITE_ROUTING_RATINGS_CREATE,
    WEBSITE_ROUTING_REGISTRATION
} from "./constant/routing/WebsiteRoutingConstants";
import {StartPage} from "./start-page/StartPage";
import {AuthContext} from "./context/AuthContext";
import {hasRecentlyLoggedIn} from "./utility/CookieUtility";
import {NavBar} from "./component/organism/navbar/NavBar";
import {NotFoundPage} from "./general-page/NotFoundPage";
import {LoadCreateRating} from "./rating/LoadCreateRating";
import {NotAuthorizedPage} from "./general-page/NotAuthorizedPage";

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
                    <Route path={WEBSITE_ROUTING_RATINGS_CREATE}
                           element={authenticated ? <LoadCreateRating/> : <NotAuthorizedPage/>}/>
                    <Route path={WEBSITE_ROUTING_NOT_FOUND} element={<NotFoundPage/>}/>
                </Routes>
            </BrowserRouter>
        </AuthContext.Provider>
    );
};
