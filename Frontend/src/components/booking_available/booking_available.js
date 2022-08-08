import React , { useEffect, useState } from "react";
import './booking_available.css'
import { Icon } from '@iconify/react';
import CustomerNavbar from "../navbar/customer_navbar";

function BookingAvailable({detail}) {
    const[price, setPrice] = useState(0);
    const[expiry, setExpiry] = useState('');
    const[code, setCode] = useState('');

    useEffect(() => {
        // this is where we should send the fetch request, sample code below
        /*fetch('/mybnb/getviewlisting/' + id, {
            method: 'GET',
            headers: {"Content-Type": "application/json"},
            credentials: "include",}).then(response =>
          response.json().then(data => {
            setListing(data);
            console.log(data);
          })
        );*/
        setPrice(80.00)
    }, []);

  return (
    <>
    <CustomerNavbar/>
    <div id="booking_available_card">
        <div id="listing_available">Listing available at this time!<Icon icon="akar-icons:circle-check-fill" inline={true} style={{ verticalAlign: '-0.2em', fontSize:'23px', marginLeft: '10px' }}/></div>
        <div id="total_cost">Total cost: ${price}</div>
        <div id="please_enter">Please enter your credit card information below:</div>
        <form>
            <div><input className='ccinfo' type="text" placeholder="Expiry Date" value={expiry} onChange={(e) => setExpiry(e.target.value)}/></div>
            <div><input className='ccinfo' type="text" placeholder="CVC Code" value={code} onChange={(e) => setCode(e.target.value)}/></div>
            <div id="confirmation"><button type="submit" id="confirm_button">Confirm</button></div>
        </form>
    </div>
    </>
  )
}

export default BookingAvailable