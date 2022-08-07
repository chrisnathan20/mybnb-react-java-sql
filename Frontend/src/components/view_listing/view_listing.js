import React , { useEffect, useState } from "react";
import SpecialPrice from './special_price/special_price';
import FutureUnavail from "./future_unavailabilities/future_unavailabilities";
import Comment from "./comments/comments";
import { Icon } from '@iconify/react';
import './view_listing.css'

function ViewListing() {
    const[listing, setListing] = useState('');
    const[start, setStart] = useState('');
    const[end, setEnd] = useState('');
    useEffect(() => {
        // this is where we should send the fetch request, sample code below
        /*fetch("http://localhost:5000/fetch-service-providers", {credentials: 'include'}).then(response =>
          response.json().then(data => {
            setAllSp(data);
          })
        );*/
        setListing(
            {
                "type": "Apartment",
                "address": "60 Town Centre Court",
                "country": "Canada",
                "city": "Scarborough",
                "postal_code": "M1P 0B1",
                "distance": 150,
                "price": 100.00,
                "amenities": "Air conditioning, Gym access"
            }
        )
    }, []);
  return (
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
                <div id="distance"><Icon icon="ri:pin-distance-line" inline={true} style={{ verticalAlign: '-0.3em', fontSize:'30px', marginRight: '7px'}}/>{listing.distance}m away</div>
                <div id="last_row_view_listing">
                    <div id="amenities"><Icon icon="bi:card-checklist" inline={true} style={{ verticalAlign: '-0.3em', fontSize:'30px', marginRight: '7px'}}/>{listing.amenities}</div>
                </div>
            </div>

            <div id="top_view_listing_right">
                <form>
                    <input className="date_booking" type="text" placeholder="Start Date" onFocus={(e) => e.target.type = 'date'} onBlur={(e) => e.target.type = 'text'} value={start} onChange={(e) => setStart(e.target.value)}/>
                    <input className="date_booking" type="text" placeholder="End Date" onFocus={(e) => e.target.type = 'date'} onBlur={(e) => e.target.type = 'text'} value={end} onChange={(e) => setEnd(e.target.value)}/>
                    <button type="submit" id="book_now">BOOK NOW</button>
                </form>
            </div>

        </div>
        <div id="view_listing_second_row">
            <div id="special_prices_view"><SpecialPrice/></div>
            <div id="future_unavail_view"><FutureUnavail/></div>
        </div>
        <div><Comment/></div>
    </body>
  )
}

export default ViewListing