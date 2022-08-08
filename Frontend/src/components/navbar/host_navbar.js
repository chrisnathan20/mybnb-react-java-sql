import React from 'react';
import './navbar.css'
import {Menu, MenuItem, IconButton} from '@mui/material';
import { Icon } from '@iconify/react';
import { useParams } from 'react-router-dom';

function HostNavbar() {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);
    const {id} = useParams();
    const myArray = id.split("&");
    const handleClick = (event) => {
      setAnchorEl(event.currentTarget);
    };
  
    const handleCloseMenu = () => {
      setAnchorEl(null);
    };
  return (
    <nav className="navigation">
        <div>
            <a href="#">
                <span className="redirection" id="home_logo"><span id="my">My</span><span id="bnb">BnB</span></span>
            </a>
        </div>
        <div id="middle_navbar">
            <div>
                <a href={"/my-listings/" + myArray[0]}>
                    <span className="redirection" id="explore">My Listings</span>
                </a>
            </div>
            <div>
                <a href={"/my-bookings-host/" + myArray[0]}>
                    <span className="redirection">My Bookings</span>
                </a>
            </div>
            <div>
                <a href="#">
                    <span className="redirection">Host Toolkit</span>
                </a>
            </div>
        </div>
        <div>
            <IconButton 
            className="icon"
            aria-controls={open ? 'basic-menu' : undefined}
            aria-haspopup="true"
            aria-expanded={open ? 'true' : undefined}
            onClick={handleClick}
            size="large"
            sx = {{  width: 60, height: 60}}
            id="icon_button">
                <Icon icon="gg:profile" inline={true} style={{ verticalAlign: '-0.2em', fontSize:'50px' }} height={100}/>
            </IconButton>
            <Menu
                id="basic-menu"
                anchorEl={anchorEl}
                open={open}
                onClose={handleCloseMenu}
                MenuListProps={{
                'aria-labelledby': 'basic-button',
                }}
            >
                <MenuItem sx={{fontSize: '15px',fontFamily: 'Poppins', color: 'black', backgroundColor: 'white', borderColor: '#d46f5e' }}><Icon icon="fluent:delete-24-filled" inline={true} style={{ verticalAlign: '-0.2em', fontSize:'23px', marginRight: '5px' }}/>Delete Account</MenuItem>
                <MenuItem sx={{ fontSize: '15px',fontFamily: 'Poppins', color: 'black', backgroundColor: 'white', borderColor: '#d46f5e' }}><Icon icon="fe:logout" inline={true} style={{ verticalAlign: '-0.2em', fontSize:'23px', marginRight: '5px' }}/>Logout</MenuItem>
            </Menu>
        </div>
  </nav>
  )
}

export default HostNavbar