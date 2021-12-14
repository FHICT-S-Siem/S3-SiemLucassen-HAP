import { useState, useEffect } from 'react'
import api from '../api/Api'
import Measurement from '../models/Measurement'
import SensorComponent from './SensorComponent'

function Room(room:string) {

    
    const [roomMeasurements, setRoomMeasurements] = useState<Measurement[]>([]);
    // Retrieve roomName from click
    const [roomNameFromClick, setRoomNameFromClick] = useState('siem');

    // Fetch MeasurementsByRoomName
    useEffect(() => {
        async function fetchMeasurements() {
            try {
                const measurements = await api.get(`/rooms/${roomNameFromClick}`)
                .then(res => res.data.measurements)
                .catch(err => []);
            setRoomMeasurements(measurements)
            } catch (e) {
                console.log("error in fetching measurements by room")
            }
        }
        fetchMeasurements();
    }, [roomNameFromClick])
    return (        
        SensorComponent(roomMeasurements)
    )
}

export default Room
