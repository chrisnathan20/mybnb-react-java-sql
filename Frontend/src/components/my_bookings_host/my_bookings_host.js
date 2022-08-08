import React , { useEffect, useState } from "react";
import BookingsUpcoming from "./my_bookings_upcoming.js";
import BookingsConfirmed from "./my_bookings_confirmed.js";
import HostNavbar from "../navbar/host_navbar.js";
import './my_bookings_host.css'
import { useNavigate, useParams } from "react-router-dom";

function MyBookingsHost() {
    const[upcoming, setUpcoming] = useState([]);
    const[confirmed, setConfirmed] = useState([]);
    const {id} = useParams(); 
    useEffect(() => {
        // this is where we should send the fetch request, sample code below
        fetch("/mybnb/getHostUpcomingListing/"+id, {credentials: 'include'}).then(response =>
          response.json().then(data => {
            setUpcoming(data);
            console.log(data);
          })
        );/*
        setUpcoming([
            {
                "username": "renterA",
                "type": "Apartment",
                "address": "60 Town Centre Court",
                "city": "Scarborough",
                "country": "Canada",
                "start_date": "2022-12-20",
                "end_date": "2022-12-29",
                "total_cost": 320,
                "booking_id": 123
            }
        ]
        )*/
    }, []);

    useEffect(() => {
        // this is where we should send the fetch request, sample code below
        fetch("/mybnb/getHostCompletedListing/"+id, {credentials: 'include'}).then(response =>
          response.json().then(data => {
            setConfirmed(data);
            console.log(data);
          })
        );/*
        setConfirmed([
            {
                "username": "renterB",
                "type": "Apartment",
                "address": "25 Town Centre Court",
                "city": "Scarborough",
                "country": "Canada",
                "start_date": "2022-12-20",
                "end_date": "2022-12-29",
                "total_cost": 450,
                "booking_id": 123 
            }
        ]
        )*/
    }, []);

    return (
        <>
        <HostNavbar/>
        <div className="all_bookings">
            <div className="bookings">
                <div className="bookings_header">Upcoming Bookings</div>
                <div className="list_of_bookings">
                    {upcoming.map((appt) => (
                            <BookingsUpcoming booking={appt} />
                    ))}
                </div>
            </div>
            <div className="bookings">
                <div className="bookings_header">Completed Bookings</div>
                <div className="list_of_bookings">
                    {confirmed.map((appt) => (
                            <BookingsConfirmed booking={appt} />
                    ))}
                </div>
            </div>
        </div>
        </>
    )
}

export default MyBookingsHost