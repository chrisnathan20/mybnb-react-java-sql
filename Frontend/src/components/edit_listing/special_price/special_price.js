import React , { useEffect, useState } from "react";
import SpecialPriceCard from './special_price_card';
import { useParams } from "react-router-dom";
import './special_price.css'

function SpecialPrice() {
    const [prices, setPrices] = useState([]);
    const{id} = useParams();

    useEffect(() => {
        // this is where we should send the fetch request, sample code below
        fetch("/mybnb/getspecialprices/"+id, {credentials: 'include'}).then(response =>
          response.json().then(data => {
            setPrices(data);
            console.log(data);
          })
        );
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