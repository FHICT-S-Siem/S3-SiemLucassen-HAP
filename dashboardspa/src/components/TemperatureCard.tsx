import React from 'react'
import Measurement from '../models/Measurement'
import './card.css'
import temp from '../svg/icons/temp.svg'
function TemperatureCard(measurement: Measurement) {
    const temperature: number = measurement.temperature; 
    return (
        <div>
            <div className='card'>
                <h2>Temperature</h2>
                <p>Average per day: ~25 C°</p>
                <img src={temp} alt='temp'/>
                <p>{((temperature === -1) ? 'Awaiting data..' : temperature)}</p>
            </div>   
        </div>
    )
}

export default TemperatureCard
