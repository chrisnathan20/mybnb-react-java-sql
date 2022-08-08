import React from 'react'
import './future_unavail_card.css'
import { useNavigate } from "react-router-dom";
import { Icon } from '@iconify/react';

function FutureUnavailCard({data}) {
    let navigate = useNavigate();
    function handleClick() {
      navigate('/login')
    }

    return (
        <div id="future_unavail_card">
            <span id="unavailable">Unavailable </span>
            <span id="from_to">
                from
                <span className='unavail_date'> {data.start_date} </span>
                to
                <span className='unavail_date'> {data.end_date}</span>
            </span>
        </div>
    )
}

export default FutureUnavailCard