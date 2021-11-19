import React, { useState } from 'react';
import { useAuth0 } from '@auth0/auth0-react'
import LoginButton from '../LoginButton';
import LogoutButton from '../LogoutButton';
import MenuItems from "./MenuItems";
import './Navbar.css';
import { Link } from 'react-router-dom';

export default function NavBar() {
    const [isClicked, setClicked] = useState(false);

    const handleClick = () => {
        setClicked(!isClicked)
    }

    const { user, isAuthenticated } = useAuth0();

    return (

        <nav className="NavbarItems">
            <h1 className="navbar-logo">HAP {isAuthenticated && <p>Welcome, {user.nickname}</p>}
            </h1>
            <div className="menu-icon" onClick={handleClick}>
                <i className={isClicked ? 'fas fa-times' : 'fas fa-bars'}></i>
            </div>
            <ul className={isClicked ? 'nav-menu active' : 'nav-menu'}>
                {MenuItems.map((item, index) => {
                    return (
                        <li key={index}>
                            <Link className={item.cName} to={item.url}>
                                {item.title}
                            </Link>
                        </li>
                    )
                })}                  
            </ul>
            {isAuthenticated ? <LogoutButton /> : <LoginButton />}
        </nav>
    )
}
