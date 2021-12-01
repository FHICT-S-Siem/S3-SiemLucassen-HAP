import React, { useState, useEffect } from 'react'
import api from '../api/Api'
import Measurement from '../models/Measurement'
import SensorComponent from './SensorComponent'

function Room() {

    const [roomMeasurements, setRoomMeasurements] = useState<Measurement[]>([]);

    const [roomName, setRoomName] = useState('siem')
    const [roomNameFromButtonClick, setRoomNameFromButtonClick] = useState('siem')

    const handleClick = () => {
        setRoomNameFromButtonClick(roomName)
    }

    // Fetch MeasurementsByRoomName
    useEffect(() => {
        async function fetchMeasurements() {
            try {
                const measurements = await api.get(`/rooms/${roomNameFromButtonClick}`)
                .then(res => res.data.measurements)
                .catch(err => []);
            console.log(measurements)
            setRoomMeasurements(measurements)
            } catch (e) {
                console.log("error in fetching measurements by room")
            }
        }
        fetchMeasurements();
    }, [roomNameFromButtonClick])

    // return roomMeasurements.map((measurement, index) => {
    //     return (
    //         <div key={index}>Temperature: {measurement.temperature}</div>
    //     )
    // });
    return (        
        SensorComponent(roomMeasurements)
    )
}

export default Room
