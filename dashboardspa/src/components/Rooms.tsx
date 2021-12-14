import React, { useState, useEffect } from 'react'
import api from '../api/Api'
import RoomModel from '../models/Room'
import Dashboard from '../views/Dashboard';
function Rooms() {

    const [allRooms, setRooms] = useState<RoomModel[]>([]);
    const [nameFromClick, setNameFromClick] = useState('siem')
    const handleClick = (name:string) => {
        setNameFromClick(name)
    }

    // Fetch AllRooms
    useEffect(() => {
        async function fetchRooms() {
            try {
                const rooms = await api.get(`/rooms`)
                .then(res => res.data.content)
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
        // TODO: Retrieve all rooms, on click transfer roomName to Room.tsx
        <div>
            <ul>
                {allRooms.map(room => (
                <h1 className="roomItems" key={room.id} onClick={() => handleClick(room.name)}>{room.name}</h1>
                ))}
            </ul>
        </div>
    )
}

export default Rooms
