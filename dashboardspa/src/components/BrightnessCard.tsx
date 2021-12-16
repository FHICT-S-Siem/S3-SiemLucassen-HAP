import React from 'react'
import Measurement from '../models/Measurement'

function BrightnessCard(measurement: Measurement) {
    const brightness: number = measurement.brightness; 
    
    return (
        <div>
            <div className='m-1 flex justify-between'>
                {((brightness === -1) ? 'Awaiting data..' : (brightness > 20) ? 'On' : 'Off') + ' ' + brightness}
            </div>            
        </div>
    )
}

export default BrightnessCard
