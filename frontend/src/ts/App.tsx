import React from 'react';
import { Routes, Route } from 'react-router-dom';
import {Registration} from "./registration/Registration";
import {Login} from "./login/Login";
import {
    WEBSITE_ROUTING_INDEX,
    WEBSITE_ROUTING_LOGIN,
    WEBSITE_ROUTING_REGISTRATION
} from "./constant/WebsiteRoutingConstants";

function App() {
  return (
      <Routes>
          {[WEBSITE_ROUTING_INDEX, WEBSITE_ROUTING_LOGIN].map((path, index)=>
              <Route path={path} key={index} element={<Login />} />)
          }
        <Route path={WEBSITE_ROUTING_REGISTRATION} element={<Registration />} />
      </Routes>
  );
}

export default App;
