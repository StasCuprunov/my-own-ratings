import React, {useContext} from 'react';
import { Routes, Route } from 'react-router-dom';
import {LoadRegistration} from "./registration/LoadRegistration";
import {AuthContext, Login} from "./login/Login";
import {
    WEBSITE_ROUTING_INDEX,
    WEBSITE_ROUTING_LOGIN,
    WEBSITE_ROUTING_REGISTRATION
} from "./constant/WebsiteRoutingConstants";
import {StartPage} from "./start-page/StartPage";

function App() {
    const authContext = useContext(AuthContext);
    return (
            <Routes>
                <Route path={WEBSITE_ROUTING_LOGIN} element={<Login />} />)
                <Route path={WEBSITE_ROUTING_INDEX} element={authContext ? <StartPage/> : <Login/>}/>
                <Route path={WEBSITE_ROUTING_REGISTRATION} element={<LoadRegistration />} />
            </Routes>
    );
}

export default App;
