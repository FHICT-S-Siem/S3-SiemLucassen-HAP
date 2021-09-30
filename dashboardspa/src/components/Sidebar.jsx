import React from 'react'
import "./sidebar.css"
import { LineStyle, People } from '@material-ui/icons'
import { useAuth0 } from '@auth0/auth0-react'
const Sidebar = () => {
    const {user, isAuthenticated} = useAuth0();
    return (
        isAuthenticated && (
        <div className="sidebar">
            <div className="sidebarWrapper">
                <div className="sidebarMenu">
                    <h3 className="sidebarTitle">Rooms</h3>
                    <ul className="sidebarList">
                        
                        <li className="sidebarListItem">
                            <LineStyle />
                            Room: 1
                        </li>
                        <li className="sidebarListItem">
                            <LineStyle />
                            Room: 2
                        </li>
                    
                    </ul>
                    <h3 className="sidebarTitle">Profile</h3>
                    <ul className="sidebarList">
                        <li className="sidebarListItem">
                            <People />
                            {user.nickname}
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        )
    )
}

export default Sidebar
