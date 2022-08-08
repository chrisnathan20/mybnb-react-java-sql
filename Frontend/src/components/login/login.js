import React, { useState } from 'react';
import './login.css';
import svg from '../../assets/house_searching.svg';
import { useNavigate } from 'react-router-dom';

function login() {
    const navigate = useNavigate();
    // Initial values is empty
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(false);

    // Form submission handler
    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(username);
        console.log(password);
        const requestbody = new Object();
        requestbody.username = username;
        requestbody.password = password;
        
        fetch('/mybnb/login', {
            method: 'POST',
            credentials: 'include',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(requestbody)
        }).then(response => {
          if (response.ok) {
            response.json().then(data => {
              console.log(data.type);
              if (data.type == "renter") {
                navigate('/explore/' + data.sessionId);
              }
              else{
                navigate('/my-listings/' + data.sessionId);
              }
            });
          }
          else if (response.status == 400) {
           setError(true);
          }
          else { // response is not ok
            throw new Error(response.statusText)
          }
          }).catch(err => {
            console.log(err)
          })
    };

  return (
    <>
        <div id="login_page">
            <div id="login_left">
                <div id="landing_slogan">
                    <div>Finding <span id="home_in_slogan">home</span></div>
                    <div>away from home.</div>
                </div>
                <div id="image"><img src={svg} alt="SVG as an image"/></div>
            </div>

            <div id="login_right">
                <div id="login_card">
                    {error && <div id="error_message_login">Incorrect username or password!</div>}
                    <form>
                        <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)}/>
                        <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)}/>
                        <button type="submit" id="login" onClick={handleSubmit}>LOG IN</button>
                    </form>
                </div>
            </div>
        </div>
    </>
  )
}

export default login