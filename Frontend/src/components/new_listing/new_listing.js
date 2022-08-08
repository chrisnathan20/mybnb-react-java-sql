import React, { useState } from 'react';
import './new_listing.css'
import svg from '../../assets/house_searching.svg'
import { useNavigate } from 'react-router-dom';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import ListItemText from '@mui/material/ListItemText';
import Select from '@mui/material/Select';
import Checkbox from '@mui/material/Checkbox';

function NewListing() {
    const navigate = useNavigate();

    // Initial values is empty
    const [address, setAddress] = useState('');
    const [country, setCountry] = useState('');
    const [city, setCity] = useState('');
    const [postalCode, setPostalCode] = useState('');
    const [longitude, setLongitude] = useState('');
    const [latitude, setLatitude] = useState('');
    const [basePrice, setBasePrice] = useState('');
    const [type, setType] = useState('');


    // handling clicking save changes button
    const handleSubmit = (e) => {
        e.preventDefault();

        var requestbody = new Object();
        requestbody.address = address;
        requestbody.country = country;
        requestbody.city = city;
        requestbody.postal_code = postalCode;

        /*fetch('mybnb/register', {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            credentials: "include",
            body: JSON.stringify(requestbody)
        }).then(response => {
            if (response.ok){
                navigate('/login')
            }
            else if (response.status == 400){
                setError(true);
            }
        })*/

    }
    
    const ITEM_HEIGHT = 48;
    const ITEM_PADDING_TOP = 8;
    const MenuProps = {
    PaperProps: {
        style: {
        maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
        width: 250,
        },
    },
    };

    const amenities = [
        'Pool Access',
        'Gym Access',
        'Wifi',
        'Kitchen',
        'Washer',
        'Dryer',
        'Pets Allowed',
        'Bathtub',
        'First-aid kit',
        'Step-free entryway',
        'Accessible bathroom'
    ];

    const [amenitiesChosen, setAmenitiesChosen] = React.useState([]);

    const handleChange = (event) => {
      const {
        target: { value },
      } = event;
      setAmenitiesChosen(
        // On autofill we get a stringified value.
        typeof value === 'string' ? value.split(',') : value,
      );
    };
    return (
        <>
            <div id="host_register_card">
                <form id="host_register_form">
                    <div id="signup_text_host">Make a New Listing</div>
                    <div className='two_col_input'>
                        <input type="text" placeholder="Address" value={address} onChange={(e) => setAddress(e.target.value)}/>
                        <input type="text" placeholder="Postal Code" value={postalCode} onChange={(e) => setPostalCode(e.target.value)}/>
                    </div>

                    <div className='two_col_input'>
                        <input id="country" type="text" placeholder="Country" value={country} onChange={(e) => setCountry(e.target.value)}/>
                        <input id="city" type="text" placeholder="City" value={city} onChange={(e) => setCity(e.target.value)}/>
                    </div>

                    <div className='two_col_input'>
                        <input type="text" placeholder="Latitude" value={latitude} onChange={(e) => setLatitude(e.target.value)}/>
                        <input type="text" placeholder="Longitude" value={longitude} onChange={(e) => setLongitude(e.target.value)}/>
                    </div>

                    <div className='two_col_input'>
                        <input id="host_dob" type="text" placeholder="Base Price" value={basePrice} onChange={(e) => setBasePrice(e.target.value)}/>
                        <input type="text" placeholder="Type" value={type} onChange={(e) => setType(e.target.value)}/>
                    </div>
                    
                    <FormControl sx={{ m: 3, width: 500 }}>
                        <InputLabel id="demo-multiple-checkbox-label">Amenities</InputLabel>
                        <Select
                        labelId="demo-multiple-checkbox-label"
                        id="demo-multiple-checkbox"
                        multiple
                        value={amenitiesChosen}
                        onChange={handleChange}
                        input={<OutlinedInput label="Amenity" />}
                        renderValue={(selected) => selected.join(', ')}
                        MenuProps={MenuProps}
                        >
                        {amenities.map((amenity) => (
                            <MenuItem key={amenity} value={amenity}>
                            <Checkbox checked={amenitiesChosen.indexOf(amenity) > -1} />
                            <ListItemText primary={amenity} />
                            </MenuItem>
                        ))}
                        </Select>
                    </FormControl>
                    <button type="submit" id="signin" onClick={handleSubmit}>Create New Listing</button>
                </form>
            </div>

        </>
    )
}

export default NewListing