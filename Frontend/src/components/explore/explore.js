import React , { useEffect, useState } from "react";
import ListingCard from './listing_card';
import './explore.css'

function explore() {
    const [listings, setListings] = useState([]);
    const [sortBy, setSortBy] = useState('distance');
    const [latitude, setLatitude] = useState(-79.1874);
    const [longitude, setLongitude] = useState(43.7830);
    const [maxDis, setMaxDis] = useState(10000);
    const [maxPrice, setMaxPrice] = useState(10000.00);
    const [minPrice, setMinPrice] = useState(0.00);
    const [address, setAddress] = useState('');
    const [city, setCity] = useState('');
    const [country, setCountry] = useState('');
    const [postalCode, setPostalCode] = useState('');
    const [start, setStart] = useState('2022-08-08');
    const [end, setEnd] = useState('2022-08-09')

    const [noFilter, setNoFilter] = React.useState(0);
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
        var requestbody = new Object();
        requestbody.sortby = sortBy;
        requestbody.latitude = latitude;
        requestbody.longitude = longitude;
        requestbody.distance = maxDis;
        requestbody.country = country;
        requestbody.city = city;
        requestbody.postalcode = postalCode;
        requestbody.minprice = minPrice;
        requestbody.maxprice = maxPrice;
        requestbody.address = address;
        requestbody.start_date = start;
        requestbody.end_date = end;

        fetch('/mybnb/getlisting', {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            credentials: "include",
            body: JSON.stringify(requestbody)
        }).then(response =>
          response.json().then(data => {
            setListings(data);
            console.log(data);
          })
        );
    }, []);

    const handleClick = (e) => {
        e.preventDefault();
        var requestbody = new Object();
        requestbody.sortby = sortBy;
        requestbody.latitude = latitude;
        requestbody.longitude = longitude;
        requestbody.distance = maxDis;
        requestbody.country = country;
        requestbody.city = city;
        requestbody.postalcode = postalCode;
        requestbody.minprice = minPrice;
        requestbody.maxprice = maxPrice;
        requestbody.address = address;
        requestbody.start_date = start;
        requestbody.end_date = end;

        fetch('/mybnb/getlisting', {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            credentials: "include",
            body: JSON.stringify(requestbody)
        }).then(response =>
          response.json().then(data => {
            setListings(data);
            console.log(data);
          })
        );

    };

    const filteredListing = React.useMemo(() => {
        if (noFilter == 0) {
            console.log('no filters');
            return listings;
        }
        else{
  
            console.log('some filters');
            return listings.filter(({ am_list }) => 
            (am_list.includes("Pool") || !(poolFilter))
            && (am_list.includes("Gym") || !(gymFilter))
            && (am_list.includes("Wifi") || !(wifiFilter))
            && (am_list.includes("Kitchen") || !(kitchenFilter))
            && (am_list.includes("Washer") || !(washerFilter))
            && (am_list.includes("Dryer") || !(dryerFilter))
            && (am_list.includes("Pets") || !(petsFilter))
            && (am_list.includes("Bathub") || !(bathtubFilter))
            && (am_list.includes("First") || !(firstAidFilter))
            && (am_list.includes("Step-free") || !(stepFilter))
            && (am_list.includes("Accessible") || !(bathroomFilter))
            )
        }
    }, [noFilter, poolFilter, gymFilter, wifiFilter, kitchenFilter, washerFilter, dryerFilter, petsFilter, bathtubFilter, firstAidFilter, stepFilter, bathroomFilter, listings])

    function incNoFilter(){
        setNoFilter(noFilter + 1);
    }
  
    function decNoFilter(){
        setNoFilter(noFilter - 1);
    }
  

    const handlePool = event => {
        if (event.target.checked) {
            incNoFilter();
          } else {
            decNoFilter();
          }
        setPoolFilter(current => !current); 
    }

    const handleGym = event => {
        if (event.target.checked) {
            incNoFilter();
          } else {
            decNoFilter();
          }
        setGymFilter(current => !current); 
    }

    const handleBathroom = event => {
        if (event.target.checked) {
            incNoFilter();
          } else {
            decNoFilter();
          }
        setBathroomFilter(current => !current); 
    }

    const handleBathtub = event => {
        if (event.target.checked) {
            incNoFilter();
          } else {
            decNoFilter();
          }
        setBathtubFilter(current => !current); 
    }

    const handleDryer = event => {
        if (event.target.checked) {
            incNoFilter();
          } else {
            decNoFilter();
          }
        setDryerFilter(current => !current); 
    }

    const handleFirst = event => {
        if (event.target.checked) {
            incNoFilter();
          } else {
            decNoFilter();
          }
        setFirstAidFilter(current => !current); 
    }

    const handleKitchen = event => {
        if (event.target.checked) {
            incNoFilter();
          } else {
            decNoFilter();
          }
        setKitchenFilter(current => !current); 
    }

    const handlePets = event => {
        if (event.target.checked) {
            incNoFilter();
          } else {
            decNoFilter();
          }
        setPetsFilter(current => !current); 
    }

    const handleStep = event => {
        if (event.target.checked) {
            incNoFilter();
          } else {
            decNoFilter();
          }
        setStepFilter(current => !current); 
    }

    const handleWasher = event => {
        if (event.target.checked) {
            incNoFilter();
          } else {
            decNoFilter();
          }
        setWasherFilter(current => !current); 
    }

    const handleWifi = event => {
        if (event.target.checked) {
            incNoFilter();
          } else {
            decNoFilter();
          }
        setWifiFilter(current => !current); 
    }


    return (
        <body id="explore_page">
            <div id="explore_left">
                <form>
                    <button type="submit" id="apply_filters" onClick={handleClick}>Apply Filters Below</button>
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
                        <label for="maxDis">Max Distance (in metres)</label><br/>
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
                        <input id="postalCode" name="postalCode" type="text" value={postalCode} onChange={(e) => setPostalCode(e.target.value)}/>
                    </div>

                    <div className='filter'>
                        <label for="start">Start Date</label><br/>
                        <input id="start" name="start" type="date" value={start} onChange={(e) => setStart(e.target.value)}/>
                    </div>

                    <div className='filter'>
                        <label for="end">End Date</label><br/>
                        <input id="end" name="end" type="date" value={end} onChange={(e) => setEnd(e.target.value)}/>
                    </div>
                    <div id="checklist">
                        <div id="amenities_header">Amenities: </div>

                        <label class="amenities">Pool Access
                            <input type="checkbox" value={poolFilter} onChange={handlePool}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Gym Access
                            <input type="checkbox" value={gymFilter} onChange={handleGym}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Wifi
                            <input type="checkbox" value={wifiFilter} onChange={handleWifi}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Kitchen
                            <input type="checkbox" value={kitchenFilter} onChange={handleKitchen}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Washer
                            <input type="checkbox" value={washerFilter} onChange={handleWasher}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Dryer
                            <input type="checkbox" value={dryerFilter} onChange={handleDryer}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Pets Allowed
                            <input type="checkbox" value={petsFilter} onChange={handlePets}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Bathtub
                            <input type="checkbox" value={bathtubFilter} onChange={handleBathtub}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">First-aid kit
                            <input type="checkbox" value={firstAidFilter} onChange={handleFirst}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Step-free Entryway
                            <input type="checkbox" value={stepFilter} onChange={handleStep}/>
                            <span class="checkmark"></span>
                        </label>

                        <label class="amenities">Accessible Bathroom
                            <input type="checkbox" value={bathroomFilter} onChange={handleBathroom}/>
                            <span class="checkmark"></span>
                        </label>
                    </div>
                </form>
            </div>

            <div id="explore_right">
                <ul>
                    {filteredListing.map(listing => (
                    <ListingCard listing={listing}></ListingCard>
                    ))}
                </ul>
            </div>
        </body>
    )
}

export default explore