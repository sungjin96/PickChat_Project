import React, { Component } from 'react';
import ReactPlayer from 'react-player';
// import './style.css';



const MainComponent = () => {
    return (
        <div style={{ marginTop: '-5%' }}>
            <center>
                <ReactPlayer
                    playsInline
                    url='https://www.youtube.com/watch?v=mPRy1B4t5YA' playing
                    width={2000}
                    height={900}
                    fluid={false}
                />
            </center>
        </div>
    );
}
export default MainComponent
