import React, { useState } from 'react';
import './make_review.css'
import { useNavigate } from 'react-router-dom';
import { Rating } from '@mui/material';

function MakeReview() {
    const navigate = useNavigate();

    // Initial values is empty
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [value, setValue] = useState(0);

    // handling clicking save changes button
    const handleSubmit = (e) => {
        e.preventDefault();

        /*var requestbody = new Object();
        requestbody.username = username;
        requestbody.password = password;
        requestbody.name = name;
        requestbody.address = address;
        requestbody.country = country;
        requestbody.city = city;
        requestbody.postal_code = postalCode;
        requestbody.dob = dob;
        requestbody.sin = sin;

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
                setError(true);
            }
        })*/
    }
    return (
        <>
            <div id="make_review_card">
                <form id="make_review_form">
                    <div id="write_a_review">Write a Review!</div>
                    <Rating
                    name="simple-controlled"
                    size='large'
                    value={value}
                    onChange={(event, newValue) => {
                        setValue(newValue);
                    }}
                    />
                    <div><input type="text" placeholder="Title" value={title} onChange={(e) => setTitle(e.target.value)}/></div>
                    <div><textarea rows="7" placeholder="Content" value={content} onChange={(e) => setContent(e.target.value)}/></div>
                    <button type="submit" onClick={handleSubmit} id="submit_review">SUBMIT REVIEW</button>
                </form>
            </div>
        </>
    )
}

export default MakeReview