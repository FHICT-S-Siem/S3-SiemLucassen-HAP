import React from 'react'
import Measurement from '../models/Measurement'
import light from '../svg/icons/light.svg'

import './card.css'
function BrightnessCard(measurement: Measurement) {
    const brightness: number = measurement.brightness; 
    return (
        <div>
            <div className='card'>
                <h3>Light</h3>
                <p>Lights on per day: ~50%</p>
                <img src={light} alt='light'/>
                <p>Currently: {((brightness === -1) ? 'Awaiting data..' : (brightness > 20) ? 'On' : 'Off')}</p>
            </div>            
        </div>
    )
}

export default BrightnessCard
