import React, { useState, useEffect } from 'react'
import measurements from '../api/measurements';
import api from '../api/measurements'
import Measurement from '../models/Measurement'
import SensorComponent from './SensorComponent'

function Room() {
    const [roomMeasurements, setRoomMeasurements] = useState<Measurement[]>([]);
    const [roomName, setRoomName] = useState('mario')
    const [roomNameFromButtonClick, setRoomNameFromButtonClick] = useState('mario')

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
                console.log("pls work :)")
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
