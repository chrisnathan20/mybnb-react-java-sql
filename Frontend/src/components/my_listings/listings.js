import React , { useEffect, useState } from "react";
import MyListingCard from './my_listings_card';
import { Icon } from "@iconify/react";
import './listings.css';
import HostNavbar from "../navbar/host_navbar";
import { Navigate, useNavigate, useParams } from "react-router-dom";

function MyListings() {

    const {id} = useParams(); 

    const[listings, setListings] = useState([]);
    useEffect(() => {
        fetch('/mybnb/getListingHost/' + id, {
            headers: {"Content-Type": "application/json"},
            credentials: "include",
        }).then(response =>
          response.json().then(data => {
            setListings(data);
            console.log(data);
          })
        );/*
        setListings([
            {
                "type": "Apartment",
                "address": "60 Town Centre Court",
                "city": "Scarborough",
                "country": "Canada",
                "postal_code": "M1P 0B1",
                "price": 200,
                "latitude": 29.495869,
                "longitude": 89.392049,
                "amenities": "Wifi, First-aid kit, Gym Access"
            },
            {
                "type": "Apartment",
                "address": "60 Town Centre Court",
                "city": "Scarborough",
                "country": "Canada",
                "postal_code": "M1P 0B1",
                "price": 200,
                "latitude": 29.495869,
                "longitude": 89.392049,
                "amenities": "Wifi, First-aid kit, Gym Access"
            }

        ])*/
    }, []);

    const navigate = useNavigate();

    const handleClick = () => {
        navigate('/new-listing/' + id);
    }

    return (
        <>
        <HostNavbar/>
        <div id="my_listings_page">
            <div id="my_listings_first">
                <div id="my_listings">My Listings</div>
                <button id="make_new_listing" onClick={handleClick}>New Listing <Icon icon="akar-icons:circle-plus-fill" inline={true} style={{ verticalAlign: '-0.2em', fontSize:'24px', marginLeft: '10px'}}/></button>
            </div>
            <div className="list_of_listings">
                {listings.map((listing) => (
                        <MyListingCard listing={listing} />
                ))}
            </div>
        </div>
        </>
    )
}

export default MyListings