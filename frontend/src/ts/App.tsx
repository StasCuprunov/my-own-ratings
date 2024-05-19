import React from 'react';
import { Routes, Route } from 'react-router-dom';
import {Registration} from "./registration/Registration";

function App() {
  return (
      <Routes>
        <Route path="/" element={<Registration />} />
        <Route path="/registration" element={<Registration />} />
      </Routes>
  );
}

export default App;
