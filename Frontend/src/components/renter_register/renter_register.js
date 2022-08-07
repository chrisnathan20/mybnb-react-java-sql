import React, { useState } from 'react';
import './renter_register.css'
import { useNavigate } from 'react-router-dom';

function renter_register() {
    const navigate = useNavigate();

    // Initial values is empty
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');
    const [address, setAddress] = useState('');
    const [country, setCountry] = useState('');
    const [city, setCity] = useState('');
    const [postalCode, setPostalCode] = useState('');
    const [dob, setDob] = useState('');
    const [sin, setSin] = useState('');
    const [payment, setPayment] = useState('');
    const [error, setError] = useState(false);

    // handling clicking save changes button
    const handleSubmit = (e) => {
        e.preventDefault();

        var requestbody = new Object();
        requestbody.username = username;
        requestbody.password = password;
        requestbody.name = name;
        requestbody.address = address;
        requestbody.country = country;
        requestbody.city = city;
        requestbody.postal_code = postalCode;
        requestbody.dob = dob;
        requestbody.sin = sin;
        requestbody.payment_info = payment;

        fetch('mybnb/register', {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            credentials: "include",
            body: JSON.stringify(requestbody)
        }).then(response => {
            if (response.ok){
                navigate('/login')
            }
            else if (response.status == 400){
                console.log("hello");
                setError(true);
                console.log(error);
            }
        })
    }
    return (
        <>
            <div id="host_register_card">
                <form id="host_register_form">
                    <div id="signup_text_host">Sign Up to Get Started as a Renter</div>
                    {error && <div id="error_message_renter">Username is taken!</div>}
                    <div className='two_col_input'>
                        <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)}/>
                        <input type="text" placeholder="Credit card number" value={payment} onChange={(e) => setPayment(e.target.value)}/>
                    </div>
                    
                    <div className='two_col_input'>
                        <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)}/>
                        <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)}/>
                    </div>

                    <div className='two_col_input'>
                        <input id="country" type="text" placeholder="Country" value={country} onChange={(e) => setCountry(e.target.value)}/>
                        <input id="city" type="text" placeholder="City" value={city} onChange={(e) => setCity(e.target.value)}/>
                    </div>

                    <div className='two_col_input'>
                        <input type="text" placeholder="Address" value={address} onChange={(e) => setAddress(e.target.value)}/>
                        <input type="text" placeholder="Postal Code" value={postalCode} onChange={(e) => setPostalCode(e.target.value)}/>
                    </div>

                    <div className='two_col_input'>
                        <input id="host_dob" type="text" placeholder="Date of Birth" onFocus={(e) => e.target.type = 'date'} onBlur={(e) => e.target.type = 'text'} value={dob} onChange={(e) => setDob(e.target.value)}/>
                        <input type="text" placeholder="SIN" value={sin} onChange={(e) => setSin(e.target.value)}/>
                    </div>

                    <button type="submit" id="signin" onClick={handleSubmit}>SIGN IN</button>
                </form>
            </div>
        </>
    )
}

export default renter_register