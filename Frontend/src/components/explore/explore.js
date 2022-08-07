import React , { useEffect, useState } from "react";
import ListingCard from './listing_card';
import './explore.css'

function explore() {
    const [listings, setListings] = useState([]);
    const [sortBy, setSortBy] = useState('Distance');
    const [latitude, setLatitude] = useState(43.7830);
    const [longitude, setLongitude] = useState(79.1874);
    const [maxDis, setMaxDis] = useState(1000);
    const [maxPrice, setMaxPrice] = useState(10000.00);
    const [minPrice, setMinPrice] = useState(0.00);
    const [address, setAddress] = useState('');
    const [city, setCity] = useState('');
    const [country, setCountry] = useState('');
    const [postalCode, setPostalCode] = useState('');

    const [poolFilter, setPoolFilter] = useState(false);
    const [gymFilter, setGymFilter] = useState(false);
    const [wifiFilter, setWifiFilter] = useState(false);
    const [kitchenFilter, setKitchenFilter] = useState(false);
    const [washerFilter, setWasherFilter] = useState(false);
    const [dryerFilter, setDryerFilter] = useState(false);
    const [petsFilter, setPetsFilter] = useState(false);
    const [bathtubFilter, setBathtubFilter] = useState(false);
    const [firstAidFilter, setFirstAidFilter] = useState(false);
    const [stepFilter, setStepFilter] = useState(false);
    const [bathroomFilter, setBathroomFilter] = useState(false);

    useEffect(() => {
        // this is where we should send the fetch request, sample code below
        /*fetch("http://localhost:5000/fetch-service-providers", {credentials: 'include'}).then(response =>
          response.json().then(data => {
            setAllSp(data);
          })
        );*/
        setListings([
            {
                type: "Apartment",
                address: "60 Town Centre Court",
                country: "Canada",
                city: "Scarborough",
                postal_code: "M1P 0B1",
                distance: 150,
                price: 100,
                amenities: "Air conditioning, Gym access"
            },
            {
                type: "House",
                address: "30 Town Centre Court",
                country: "Canada",
                city: "Scarborough",
                postal_code: "M1P 4K9",
                distance: 1000,
                price: 125,
                amenities: "First aid kit, Gym access"
            },
            {
                type: "Apartment",
                address: "60 Town Centre Court",
                country: "Canada",
                city: "Scarborough",
                postal_code: "M1P 0B1",
                distance: 150,
                price: 100,
                amenities: "Air conditioning, Gym access"
            },
            {
                type: "Apartment",
                address: "60 Town Centre Court",
                country: "Canada",
                city: "Scarborough",
                postal_code: "M1P 0B1",
                distance: 150,
                price: 100,
                amenities: "Air conditioning, Gym access"
            },
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
        ]
        )
    }, []);

    return (
        <body id="explore_page">
            <div id="explore_left">
                <form>
                <button type="submit" id="apply_filters">Apply Filters Below</button>
                    <div className='filter'>
                        <label for="sortBy">Sort By</label><br/>
                        <input id="sortBy" name="sortBy" type="text" value={sortBy} onChange={(e) => setSortBy(e.target.value)}/>
                    </div>

                    <div className='filter'>
                        <label for="latitude">Latitude</label><br/>
                        <input id="latitude" name="latitude" type="text" value={latitude} onChange={(e) => setLatitude(e.target.value)}/>
                    </div>

                    <div className='filter'>
                        <label for="longitude">Longitude</label><br/>
                        <input id="longitude" name="longitude" type="text" value={longitude} onChange={(e) => setLongitude(e.target.value)}/>
                    </div>

                    <div className='filter'>
                        <label for="maxDis">Max Distance</label><br/>
                        <input id="maxDis" name="maxDis" type="text" value={maxDis} onChange={(e) => setMaxDis(e.target.value)}/>
                    </div>

                    <div className='filter'>
                        <label for="minPrice">Min Price</label><br/>
                        <input id="minPrice" name="minPrice" type="text" value={minPrice} onChange={(e) => setMinPrice(e.target.value)}/>
                    </div>

                    <div className='filter'>
                        <label for="maxPrice">Max Price</label><br/>
                        <input id="maxPrice" name="maxPrice" type="text" value={maxPrice} onChange={(e) => setMaxPrice(e.target.value)}/>
                    </div>

                    <div className='filter'>
                        <label for="address">Address</label><br/>
                        <input id="address" name="address" type="text" value={address} onChange={(e) => setAddress(e.target.value)}/>
                    </div>

                    <div className='filter'>
                        <label for="city">City</label><br/>
                        <input id="city" name="city" type="text" value={city} onChange={(e) => setCity(e.target.value)}/>
                    </div>

                    <div className='filter'>
                        <label for="country">Country</label><br/>
                        <input id="country" name="country" type="text" value={country} onChange={(e) => setCountry(e.target.value)}/>
                    </div>

                    <div className='filter'>
                        <label for="postalCode">Postal Code</label><br/>
                        <input id="postalCode" name="postalCOde" type="text" value={postalCode} onChange={(e) => setPostalCode(e.target.value)}/>
                    </div>
                    <div id="checklist">
                        <div id="amenities_header">Amenities: </div>

                        <label class="amenities">Pool Access
                            <input type="checkbox" value={poolFilter}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Gym Access
                            <input type="checkbox" value={gymFilter}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Wifi
                            <input type="checkbox" value={wifiFilter}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Kitchen
                            <input type="checkbox" value={kitchenFilter}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Washer
                            <input type="checkbox" value={washerFilter}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Dryer
                            <input type="checkbox" value={dryerFilter}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Pets Allowed
                            <input type="checkbox" value={petsFilter}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Bathtub
                            <input type="checkbox" value={bathtubFilter}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">First-aid kit
                            <input type="checkbox" value={firstAidFilter}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Step-free Entryway
                            <input type="checkbox" value={stepFilter}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Accessible Bathroom
                            <input type="checkbox" value={bathroomFilter}/>
                            <span class="checkmark"></span>
                        </label>
                    </div>
                </form>
            </div>

            <div id="explore_right">
                <ul>
                    {listings.map(listing => (
                    <ListingCard listing={listing}></ListingCard>
                    ))}
                </ul>
            </div>
        </body>
    )
}

export default explore