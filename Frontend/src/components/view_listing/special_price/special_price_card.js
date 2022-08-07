import React from 'react'
import './special_price_card.css'
import { useNavigate } from "react-router-dom";
import { Icon } from '@iconify/react';

function SpecialPriceCard({data}) {
    let navigate = useNavigate();
    function handleClick() {
      navigate('/login')
    }

    return (
        <div id="special_price_card">
            <div id="special_price_card_left">
                <span id="data_price">${data.price}</span><span id="per_night_special">/night</span>
            </div>
            <div id="special_price_card_right">
                <div id="start_date">from <span className='dates'>{data.start_date}</span></div>
                <div id="end_date">from <span className='dates'>{data.end_date}</span></div>
            </div>
        </div>
    )
}

export default SpecialPriceCard