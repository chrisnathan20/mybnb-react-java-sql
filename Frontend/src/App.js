import './App.css';
import React from 'react';
import LandingPage from './components/landing_page/landing_page';
import LoginPage from './components/login/login';
import Home from './components/home/home';
import InitialRegister from './components/initial_register/initial_register';
import HostRegister from './components/host_register/host_register';
import RenterRegister from './components/renter_register/renter_register';
import Explore from './components/explore/explore';
import ViewListing from './components/view_listing/view_listing';
import BookingAvailable from './components/booking_available/booking_available';
import MyBookings from './components/my_bookings/my_bookings';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link
} from "react-router-dom";

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path='/' element={<LandingPage/>} />
          <Route path='/login' element={<LoginPage/>} />
          <Route path = '/home/:id' element={<Home/>}/>
          <Route path = '/initial-register' element={<InitialRegister/>}/>
          <Route path = '/host-register' element={<HostRegister/>}/>
          <Route path = '/renter-register' element={<RenterRegister/>}/>
          <Route path = '/view-listing/:id' element={<ViewListing/>}/>
          <Route path = '/explore/:id' element={<Explore/>}/>
          <Route path = '/booking-available/:id' element={<BookingAvailable/>}/>
          <Route path = '/my-bookings/:id' element={<MyBookings/>}/>
        </Routes>
      </Router>
    </>
  );
}

export default App;
