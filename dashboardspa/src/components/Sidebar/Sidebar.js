import React from 'react'
import Rooms from '../../components/Rooms'
import Location from '../../components/Location'

import '../../App.css'
function Sidebar() {
    return (
        <>
            <div className="Sidebar">
                {/* Location */}
                <ul>
                    <li className="locationItem">
                        <Location/>
                    </li>
                    <li>
                        <Rooms/>
                    </li>
                </ul>
                {/* Retrieve all rooms*/}
            </div>

            <div className="Sidebar-mobile">
                {/* Location */}
                
                <Rooms/>
                
                {/* Retrieve all rooms*/}
            </div>
        </>
    )
}

export default Sidebar
