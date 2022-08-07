import React , { useEffect, useState } from "react";
import SpecialPriceCard from './special_price_card';
import './special_price.css'

function SpecialPrice() {
    const [prices, setPrices] = useState([]);

    useEffect(() => {
        // this is where we should send the fetch request, sample code below
        /*fetch("http://localhost:5000/fetch-service-providers", {credentials: 'include'}).then(response =>
          response.json().then(data => {
            setAllSp(data);
          })
        );*/
        setPrices([
            {
                "price": 80.00,
                "start_date": "2022-04-07",
                "end_date": "2022-05-07"
            },
            {
                "price": 80.00,
                "start_date": "2022-04-07",
                "end_date": "2022-05-07"
            },
            {
                "price": 80.00,
                "start_date": "2022-04-07",
                "end_date": "2022-05-07"
            },
        ]
        )
    }, []);

    return (
        <body id="special_prices">
            <div id="special_prices_heading">Special Prices</div>
            <div id="all_special_prices">
                <ul>
                    {prices.map(price => (
                    <SpecialPriceCard data={price}></SpecialPriceCard>
                    ))}
                </ul>
            </div>
        </body>
    )
}

export default SpecialPrice