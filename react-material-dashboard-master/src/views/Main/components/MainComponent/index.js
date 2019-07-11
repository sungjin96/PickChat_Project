import React, { Component } from 'react';
import ReactPlayer from 'react-player';
import './style.css';
import { white } from 'common/colors';


const useFadeIn = () =>{
    const element = React.useRef();
    React.useEffect(() => {
    if(element.current) {
        const {current} = element;
        current.style.transition = `opacity 7s`;
        current.style.opacity = 1;
    }
    }, [])
    return {ref: element, style: {opacity: 0}}
}


const MainComponent = () => {

    
    
    const el = useFadeIn();    
    return (
        <div style={{ marginTop: '3%' }}> 
        <div className="test"></div>
            <center><div {...el} className="mainpic">
            <h1 style={{marginTop:'13%', fontSize:"6rem", color:'white'}}>EveryBody,</h1>
            <h1 style={{marginTop:'5%', fontSize:"5rem", color:'white'}}>P I C K  &  C H A T</h1></div>
            
            </center>
                {/* <ReactPlayer
                    playsInline
                    url='https://www.youtube.com/watch?v=mPRy1B4t5YA' playing
                    width={2000}
                    height={900}
                    fluid={false}
                /> */}
           
        </div>
    );
}

export default MainComponent
