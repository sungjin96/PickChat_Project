import React, { Component } from 'react';

import './style.css';
import { white } from 'common/colors';


const useFadeIn = () =>{
    const element = React.useRef();
    React.useEffect(() => {
    if(element.current) {
        const {current} = element;
        current.style.transition = `opacity 4s`;
        current.style.opacity = 1;
    }
    }, [])
    return {ref: element, style: {opacity: 0}}
}


const MainComponent = () => {

    
    
    const el = useFadeIn();    
    return (
        <div style={{ marginTop: '3%' }}> 
            <center><div {...el} className="mainpic">
            <h1 style={{marginTop:'10%', fontSize:"7rem", color:'white', letterSpacing:'15px'}}>EVERYBODY</h1>
            <h1 style={{marginTop:'6%', fontSize:"4rem", color:'white'}}>P I C K  &  C H A T</h1></div>            
            </center>                
        </div>
    );
}

export default MainComponent
