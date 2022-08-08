import React from 'react';
import './my_bookings_card.css'

function BookingsConfirmed({booking}) {
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
            <button id="cancel">WRITE A REVIEW</button>
        </div>
        <div>
        </div>
    </div>
  )
}

export default BookingsConfirmed