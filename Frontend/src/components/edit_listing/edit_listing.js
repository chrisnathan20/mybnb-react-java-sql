import React , { useEffect, useState } from "react";
import SpecialPrice from './special_price/special_price';
import FutureUnavail from "./future_unavailabilities/future_unavailabilities";
import { Icon } from '@iconify/react';
import './edit_listing.css'
import { useNavigate, useParams } from "react-router-dom";
import CustomerNavbar from "../navbar/customer_navbar";

function EditListing() {
    const[listing, setListing] = useState('');
    const[start, setStart] = useState('');
    const[end, setEnd] = useState('');
    const[start1, setStart1] = useState('');
    const[end1, setEnd1] = useState('');
    const[price, setPrice] = useState('');
    const[error, setError] = useState(false);
    
    const navigate = useNavigate();
    const {id} = useParams(); 

    console.log(id);

    const handleClick = (e) => {
        e.preventDefault();

        var requestbody = new Object();
        requestbody.start_date = start1;
        requestbody.end_date = end1;
        requestbody.price = price;
        fetch('/mybnb/setSpecial/' + id, {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            credentials: "include",
            body: JSON.stringify(requestbody)
        }).then(response => {
            if (response.ok){
                response.json().then(data => {
                    window.location.reload();
                });
            }
            else if (response.status == 400){
                setError(true);
            }
        })
    }

    const handleClickU = (e) => {
        e.preventDefault();

        var requestbody = new Object();
        requestbody.start_date = start;
        requestbody.end_date = end;
        fetch('/mybnb/setUnavailable/' + id, {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            credentials: "include",
            body: JSON.stringify(requestbody)
        }).then(response => {
            if (response.ok){
                response.json().then(data => {
                    window.location.reload();
                });
            }
            else if (response.status == 400){
                setError(true);
            }
        })
    }
  return (
    <>
    <CustomerNavbar/>
    <body id="view_listing_page">
        <div id="view_listing_second_row">
            <div id="special_prices_view"><SpecialPrice/></div>
            <div id="future_unavail_view"><FutureUnavail/></div>
        </div>
        <div id="inputs_editing">
            <div className="edit_forms">
                <form>
                    {error && <div id="unavail_message">Unable to add due to conflict!</div>}
                    <div className="enter_new">Enter new special price: </div>
                    <input className="edit_listing" type="text" placeholder="Start Date" onFocus={(e) => e.target.type = 'date'} onBlur={(e) => e.target.type = 'text'} value={start1} onChange={(e) => setStart1(e.target.value)}/>
                    <input className="edit_listing" type="text" placeholder="End Date" onFocus={(e) => e.target.type = 'date'} onBlur={(e) => e.target.type = 'text'} value={end1} onChange={(e) => setEnd1(e.target.value)}/>
                    <input className="edit_listing" type="text" placeholder="New Special Price"  value={price} onChange={(e) => setPrice(e.target.value)}/>
                    <div className="bottom_button"><button type="submit" className="add_edit" onClick={handleClick}>ADD</button></div>
                </form>
            </div>

            <div className="edit_forms">
                <form>
                    {error && <div id="unavail_message">Unable to add due to conflict!</div>}
                    <div className="enter_new">Enter new unavailable date: </div>
                    <input className="edit_listing" type="text" placeholder="Start Date" onFocus={(e) => e.target.type = 'date'} onBlur={(e) => e.target.type = 'text'} value={start} onChange={(e) => setStart(e.target.value)}/>
                    <input className="edit_listing" type="text" placeholder="End Date" onFocus={(e) => e.target.type = 'date'} onBlur={(e) => e.target.type = 'text'} value={end} onChange={(e) => setEnd(e.target.value)}/>
                    <div className="bottom_button"><button type="submit" className="add_edit" onClick={handleClickU}>ADD</button></div>
                </form>
            </div>
        </div>
    </body>
    </>
  )
}

export default EditListing