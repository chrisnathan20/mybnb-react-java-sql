import React , { useEffect, useState } from "react";
import SpecialPrice from './special_price/special_price';
import FutureUnavail from "./future_unavailabilities/future_unavailabilities";
import { Icon } from '@iconify/react';
import './edit_listing.css'
import { useNavigate, useParams } from "react-router-dom";
import CustomerNavbar from "../navbar/customer_navbar";

function EditListing() {
    const[listing, setListing] = useState('');
    const[start, setStart] = useState('');
    const[end, setEnd] = useState('');
    const[error, setError] = useState(false);
    
    const navigate = useNavigate();
    const {id} = useParams(); 

    console.log(id);

    const handleClick = (e) => {
        e.preventDefault();

        var requestbody = new Object();
        requestbody.start_date = start;
        requestbody.end_date = end;
        fetch('/mybnb/attemptbooking/' + id, {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            credentials: "include",
            body: JSON.stringify(requestbody)
        }).then(response => {
            if (response.ok){
                response.json().then(data => {
                    navigate('/booking-available/' + id + '&' + start + '&' + end + '&' + data);
                });
            }
            else if (response.status == 400){
                setError(true);
            }
        })
    }
  return (
    <>
    <CustomerNavbar/>
    <body id="view_listing_page">
        <div id="view_listing_second_row">
            <div id="special_prices_view"><SpecialPrice/></div>
            <div id="future_unavail_view"><FutureUnavail/></div>
        </div>
        <div>
        <form>
                    <input className="date_booking" type="text" placeholder="Start Date" onFocus={(e) => e.target.type = 'date'} onBlur={(e) => e.target.type = 'text'} value={start} onChange={(e) => setStart(e.target.value)}/>
                    <input className="date_booking" type="text" placeholder="End Date" onFocus={(e) => e.target.type = 'date'} onBlur={(e) => e.target.type = 'text'} value={end} onChange={(e) => setEnd(e.target.value)}/>
                    <button type="submit" id="book_now" onClick={handleClick}>BOOK NOW</button>
                </form>
        </div>
    </body>
    </>
  )
}

export default EditListing