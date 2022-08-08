import React from 'react';
import './my_bookings_card.css'

function BookingsUpcoming({booking}) {

  const handleClick = () => {
    var requestbody = new Object();
    requestbody.booking_id = booking.booking_id;

    fetch('/mybnb/cancelbooking', {
        method: 'POST',
        headers: {"Content-Type": "application/json"},
        credentials: "include",
        body: JSON.stringify(requestbody)
    }).then(response => {
        if (response.ok){
            window.location.reload();
        }
    });
  }

  return (
    <div id="booking_upcoming_card">
        <div id="upcoming_card_first" className="upcoming_card_row">
            <div id="booking_header">{booking.type} at {booking.address}</div>
            <div id="booking_total">${booking.total_cost}</div>
        </div>
        <div className='upcoming_body'>{booking.city}, {booking.country}</div>
        <div className='upcoming_body'>{booking.username}</div>
        <div className="upcoming_card_row">
            <div className='upcoming_body'>from {booking.start_date} to {booking.end_date}</div>
            <button id="cancel" onClick={handleClick}>CANCEL</button>
        </div>
        <div>
        </div>
    </div>
  )
}

export default BookingsUpcoming