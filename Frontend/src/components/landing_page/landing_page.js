import React from 'react'
import './landing_page.css'
import svg from '../../assets/house_searching.svg'

function landing_page() {
  return (
    <>
        <div id="landing_page">
            <div id="left">
                <div id="landing_slogan">
                    <div>Finding <span id="home_in_slogan">home</span></div>
                    <div>away from home.</div>
                </div>
                <div id="image"><img src={svg} alt="SVG as an image"/></div>
            </div>
            <div id="right">
                <div id="landing_right_card">
                    <div id="top_text">
                        <div id="welcome_text">Welcome to <span id="mybnb"><span id="my">My</span><span id="bnb">BnB</span></span>!</div>
                        <div id="start_by">Start by...</div>
                    </div>
                    <button id="login">LOG IN</button>
                    <br/>
                    <button id="signup">SIGN UP</button>
                </div>
            </div>
        </div>
    </>
  )
}

export default landing_page