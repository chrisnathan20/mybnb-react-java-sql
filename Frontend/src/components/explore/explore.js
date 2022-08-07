import React , { useEffect, useState } from "react";
import ListingCard from './listing_card';

function explore() {
    const [listing, setListing] = useState('');
    useEffect(() => {
        // this is where we should send the fetch request, sample code below
        /*fetch("http://localhost:5000/fetch-service-providers", {credentials: 'include'}).then(response =>
          response.json().then(data => {
            setAllSp(data);
          })
        );*/
        setListing(
            {
                type: "Apartment",
                address: "60 Town Centre Court",
                country: "Canada",
                city: "Scarborough",
                postal_code: "M1P 0B1",
                distance: 150,
                price: 100,
                amenities: "Air conditioning, Gym access"
            }
        )
    }, []);

    return (
        <ListingCard listing={listing}></ListingCard>
    )
}

export default explore