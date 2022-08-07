import React , { useEffect, useState } from "react";
import FutureUnavailCard from "./future_unavail_card";
import './future_unavailabilities.css'

function FutureUnavail() {
    const [unavail, setUnavail] = useState([]);

    useEffect(() => {
        // this is where we should send the fetch request, sample code below
        /*fetch("http://localhost:5000/fetch-service-providers", {credentials: 'include'}).then(response =>
          response.json().then(data => {
            setAllSp(data);
          })
        );*/
        setUnavail([
            {
                "start_date": "2022-04-07",
                "end_date": "2022-05-07"
            },
            {
                "start_date": "2022-04-07",
                "end_date": "2022-05-07"
            },
            {
                "start_date": "2022-04-07",
                "end_date": "2022-05-07"
            },
            {
                "start_date": "2022-04-07",
                "end_date": "2022-05-07"
            }
        ]
        )
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