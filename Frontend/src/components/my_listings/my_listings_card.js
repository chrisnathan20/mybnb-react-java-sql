import React from 'react'
import './my_listings_card.css'
import { useNavigate, useParams } from "react-router-dom";
import { Icon } from '@iconify/react';

function MyListingCard({listing}) {
    const { id } = useParams();

    let navigate = useNavigate();
    function handleEdit(){
        console.log("yes i am editing");
        navigate('/edit-listing/'+ id + '&' + listing.listingId)
    }

    return (
        <div id="listing_card">
            <div id="first_row">
                <div id="header">{listing.type} at {listing.address}</div>
                <div id="price">${listing.price}</div>
            </div>
            <div id="second_row">
                <div id="location"><Icon icon="fa6-solid:map-location-dot" inline={true} style={{ verticalAlign: '-0.2em', fontSize:'22px', marginRight: '7px'}}/>{listing.city}, {listing.country} {listing.postal_code}</div>
                <div id="per_night">per night</div>
            </div>
            <div id="last_row">
                <div id="distance"><Icon icon="tabler:world" inline={true} style={{ verticalAlign: '-0.3em', fontSize:'30px', marginRight: '7px'}}/>{listing.latitude}, {listing.longitude}</div>
                <button id="delete_listing" handleClick={handleEdit}>Edit Listing</button>
            </div>
            <div id="last_row">
                <div id="amenities"><Icon icon="bi:card-checklist" inline={true} style={{ verticalAlign: '-0.3em', fontSize:'30px', marginRight: '7px'}}/>{listing.amenities}</div>
                <button id="delete_listing">Delete Listing</button>
            </div>
        </div>
    )
}

export default MyListingCard