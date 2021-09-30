import React from 'react'
import "./topbar.css"
import { useAuth0 } from '@auth0/auth0-react'
import LogoutButton from './LogoutButton';


const Topbar = () => {
    const {user, isAuthenticated } = useAuth0();
    return (
        isAuthenticated && (
        <div className="topbar">
            <div className="topbarWrapper">
                <div className="topLeft">
                    <span className="logo">{user.nickname}</span>
                </div>
                <div className="topRight">
                    <div className="topAvatar">
                        <img src={user.picture} alt={user.name} />
                    </div>
                    
                    <LogoutButton />
                </div>
            </div>
        </div>        
        )
    )
}


export default Topbar