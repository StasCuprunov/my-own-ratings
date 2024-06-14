import React, {useState} from 'react';
import { Routes, Route, BrowserRouter } from 'react-router-dom';
import "../css/bundle-min.css";
import {LoadRegistration} from "./registration/LoadRegistration";
import {Login} from "./authentication/login/Login";
import {Logout} from "./authentication/logout/Logout";
import {
    WEBSITE_ROUTING_INDEX,
    WEBSITE_ROUTING_LOGIN,
    WEBSITE_ROUTING_LOGOUT,
    WEBSITE_ROUTING_NOT_FOUND,
    WEBSITE_ROUTING_RATINGS,
    WEBSITE_ROUTING_RATINGS_BY_ID,
    WEBSITE_ROUTING_RATINGS_CREATE,
    WEBSITE_ROUTING_RATINGS_EDIT_BY_ID,
    WEBSITE_ROUTING_REGISTRATION
} from "./constant/routing/WebsiteRoutingConstants";
import {AuthContext} from "./context/AuthContext";
import {hasRecentlyLoggedIn} from "./utility/CookieUtility";
import {NavBar} from "./component/organism/navbar/NavBar";
import {NotFound} from "./general-page/not-found/NotFound";
import {LoadCreateRating} from "./rating/form/create/LoadCreateRating";
import {LoadRating} from "./rating/get/LoadRating";
import {LoadStartPage} from "./start-page/LoadStartPage";
import {LoadEditRating} from "./rating/form/edit/LoadEditRating";
import {Footer} from "./component/organism/Footer";

export const App = () => {
    const [authenticated, setAuthenticated] = useState(hasRecentlyLoggedIn());
    return (
        <AuthContext.Provider value={{ authenticated, setAuthenticated }}>
            <BrowserRouter>
                <NavBar />
                <Routes>
                    <Route path={WEBSITE_ROUTING_LOGIN} element={<Login />} />)
                    <Route path={WEBSITE_ROUTING_LOGOUT} element={<Logout />}/>
                    <Route path={WEBSITE_ROUTING_INDEX} element={authenticated ? <LoadStartPage/> : <Login/>}/>
                    <Route path={WEBSITE_ROUTING_RATINGS} element={<LoadStartPage/>} />
                    <Route path={WEBSITE_ROUTING_REGISTRATION} element={<LoadRegistration/>} />
                    <Route path={WEBSITE_ROUTING_RATINGS_CREATE} element={<LoadCreateRating/>} />
                    <Route path={WEBSITE_ROUTING_RATINGS_BY_ID} element={<LoadRating/>} />
                    <Route path={WEBSITE_ROUTING_RATINGS_EDIT_BY_ID} element={<LoadEditRating/>} />
                    <Route path={WEBSITE_ROUTING_NOT_FOUND} element={<NotFound/>}/>
                </Routes>
                <Footer/>
            </BrowserRouter>
        </AuthContext.Provider>
    );
};
