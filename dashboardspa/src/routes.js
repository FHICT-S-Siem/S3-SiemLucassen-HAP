import React from 'react';
import Home from './views/Home';
import Dashboard from './views/Dashboard';
import { Route, Routes } from 'react-router-dom';
import './App.css';
export const HAPRoutes = () => {
  return (
    <div className="content">
      <Routes>
        <Route exact path='/' element={<Home/>} />
        <Route exact path='/Home' element={<Home/>} />
        <Route exact path='/Dashboard' element={<Dashboard/>} />
      </Routes>
    </div>
  );
};