import React , { useEffect, useState } from "react";
import FutureUnavailCard from "./future_unavail_card";
import './future_unavailabilities.css';
import { useNavigate, useParams } from "react-router-dom";

function FutureUnavail() {
    const [unavail, setUnavail] = useState([]);
    const{id} = useParams();
    console.log("unavailbility: "+ id);

    useEffect(() => {
        // this is where we should send the fetch request, sample code below
        fetch("/mybnb/getunavailability/"+id, {credentials: 'include'}).then(response =>
          response.json().then(data => {
            setUnavail(data);
            console.log(data);
          })
        );
    }, []);

    return (
        <body id="future_unavail">
            <div id="special_prices_heading">Future Unavailabilities</div>
            <div id="all_special_prices">
                <ul>
                    {unavail.map(date => (
                    <FutureUnavailCard data={date}></FutureUnavailCard>
                    ))}
                </ul>
            </div>
        </body>
    )
}

export default FutureUnavail