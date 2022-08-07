import React from 'react'
import './initial_register.css'
import svg from '../../assets/house_searching.svg'
import { useNavigate } from 'react-router-dom';

function initial_register() {
    const navigate = useNavigate();

    const handleHost = (e) => {
        navigate('/host-register');
    }

    const handleRenter = (e) => {
        navigate('/login');
    }
    return (
        <>
            <div id="initial_register">
                <div id="initial_register_left">
                    <div id="landing_slogan">
                        <div>Finding <span id="home_in_slogan">home</span></div>
                        <div>away from home.</div>
                    </div>
                    <div id="image"><img src={svg} alt="SVG as an image"/></div>
                </div>
                <div id="initial_register_right">
                    <div id="register_as_card">
                        <div id="top_text">
                            <div id="new_to">New to <span id="mybnb"><span id="my">My</span><span id="bnb">BnB</span></span>?</div>
                            <div id="second">I am a...</div>
                        </div>
                        <button id="login" onClick={handleHost}>HOST</button>
                        <br/>
                        <button id="signup" onClick={handleRenter}>RENTER</button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default initial_register