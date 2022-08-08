import React , { useEffect, useState } from "react";
import SpecialPrice from './special_price/special_price';
import FutureUnavail from "./future_unavailabilities/future_unavailabilities";
import Comment from "./comments/comments";
import { Icon } from '@iconify/react';
import './view_listing.css'
import { useNavigate, useParams } from "react-router-dom";
import CustomerNavbar from "../navbar/customer_navbar";

function ViewListing() {
    const[listing, setListing] = useState('');
    const[start, setStart] = useState('');
    const[end, setEnd] = useState('');
    const[error, setError] = useState(false);
    
    const navigate = useNavigate();
    const {id} = useParams(); 

    console.log(id);

    useEffect(() => {
        // this is where we should send the fetch request, sample code below
        fetch('/mybnb/getviewlisting/' + id, {
            method: 'GET',
            headers: {"Content-Type": "application/json"},
            credentials: "include",}).then(response =>
          response.json().then(data => {
            setListing(data);
            console.log(data);
          })
        );
    }, []);

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
        <div id="top_view_listing">
            <div id="view_listing_card">
                <div id="first_row_view_listing">
                    <div id="header">{listing.type} at {listing.address}</div>
                    <div id="price">${listing.price}</div>
                </div>
                <div id="second_row_view_listing">
                    <div id="location"><Icon icon="fa6-solid:map-location-dot" inline={true} style={{ verticalAlign: '-0.2em', fontSize:'22px', marginRight: '7px'}}/>{listing.city}, {listing.country} {listing.postal_code}</div>
                    <div id="per_night">per night</div>
                </div>
                <div id="distance"><Icon icon="tabler:world" inline={true} style={{ verticalAlign: '-0.3em', fontSize:'30px', marginRight: '7px'}}/>{listing.latitude}, {listing.longitude}</div>
                <div id="last_row_view_listing">
                    <div id="amenities"><Icon icon="bi:card-checklist" inline={true} style={{ verticalAlign: '-0.3em', fontSize:'30px', marginRight: '7px'}}/>{listing.amenities}</div>
                </div>
            </div>

            <div id="top_view_listing_right">
                <form>
                    {error && <div id="unavail_message">Listing is uvailable on those dates!</div>}
                    <input className="date_booking" type="text" placeholder="Start Date" onFocus={(e) => e.target.type = 'date'} onBlur={(e) => e.target.type = 'text'} value={start} onChange={(e) => setStart(e.target.value)}/>
                    <input className="date_booking" type="text" placeholder="End Date" onFocus={(e) => e.target.type = 'date'} onBlur={(e) => e.target.type = 'text'} value={end} onChange={(e) => setEnd(e.target.value)}/>
                    <button type="submit" id="book_now" onClick={handleClick}>BOOK NOW</button>
                </form>
            </div>

        </div>
        <div id="view_listing_second_row">
            <div id="special_prices_view"><SpecialPrice/></div>
            <div id="future_unavail_view"><FutureUnavail/></div>
        </div>
        <div><Comment/></div>
    </body>
    </>
  )
}

export default ViewListing