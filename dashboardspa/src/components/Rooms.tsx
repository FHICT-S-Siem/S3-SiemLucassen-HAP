import React, { useState, useEffect } from 'react'
import api from '../api/Api'
import Room from '../models/Room'


function Rooms() {
    
    const [allRooms, setRooms] = useState<Room[]>([]);

    // Fetch AllRooms
    useEffect(() => {
        async function fetchRooms() {
            try {
                const rooms = await api.get(`/rooms`)
                .then(res => res.data.rooms)
                .catch(err => []);
            console.log(rooms)
            setRooms(rooms)
            } catch (e) {
                console.log("error in fetching rooms")
            }
        }
        fetchRooms();
    }, [])
    return (
        <div>
            <li>
                <h1 className="roomItems active">Siem</h1>
            </li>
            <li>
                <h1 className="roomItems">Mario</h1>
            </li>
        </div>
    )
}

export default Rooms
