import { useState, useEffect  } from 'react'
import api from '../api/Api'
import Measurement from '../models/Measurement'
import BrightnessCard from './BrightnessCard';
import SensorComponent from './SensorComponent'
import TemperatureCard from './TemperatureCard';
import '../../src/App.css'
import './room.css'

function Room(room:string) {

    
    const [roomMeasurements, setRoomMeasurements] = useState<Measurement[]>([]);
    // Retrieve roomName from click
    const [roomNameFromClick, setRoomNameFromClick] = useState('siem');
    const [lastMeasurement, setLastMeasurement]= useState<Measurement>({ brightness: -1, datetime: new Date(), id: -1, roomName: 'siem', temperature: -1});

    // Fetch MeasurementsByRoomName
    useEffect(() => {
        function compareId(a: Measurement, b: Measurement) {
            return a.id - b.id;
        }
        async function fetchMeasurements() {
            try {
                const measurements = await api.get(`/rooms/${roomNameFromClick}`)
                .then(res => res.data.measurements)
                .catch(err => []);

                measurements.sort(compareId);
                
                // measurements.array.forEach((measurement: Measurement) => {
                //     measurement.datetime = new Date(new Date(measurement.datetime).toUTCString())
                // });

                setRoomMeasurements(measurements)
                console.log(measurements)
                setLastMeasurement(measurements[measurements.length - 1])

            } catch (e) {
                console.log("error in fetching measurements by room")
            }
        }
        fetchMeasurements();
    }, [roomNameFromClick])
    // const lastMeasurement = roomMeasurements.filter(p => p.datetime).at(-1)?.brightness;
    // const brightness = lastMeasurement ? lastMeasurement : -1;
    return (
        [
        <div className='card-wrapper'>
            {TemperatureCard(lastMeasurement!)}
            {BrightnessCard(lastMeasurement!)}
        </div>,
        SensorComponent(roomMeasurements)
        ]
    )
}

export default Room
