import React from 'react'
import "./home.css"
import Plot from 'react-plotly.js';

const Home = () => {
    return (
        <div className="home">
            <h1 className="shadow">House Assistant Platform</h1>
            <p>
            The goal of this project is to have a wall mounted tablet running a dashboard to control for my room.
            <br/>
            The goal is really to have one screen without any menu so I can have a global view of the more important thing in the home: temperature, lights, camera.
            </p>
            <br/>
            <Plot
                data={[
                {
                    x: [1, 2, 3, 4],
                    y: [2, 6, 3, 6],
                    type: 'scatter',
                    mode: 'lines+markers',
                    marker: {color: 'blue'},
                },
                {type: 'bar', x: [1, 2, 3, 4], y: [2, 5, 3, 5]},
                ]}
                layout={ {width: 320, height: 240, title: 'Plot'} }
            />
        </div>
    )
}

export default Home
