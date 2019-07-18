import React from 'react';
import './style.css';

const SubComponent = ({ content, content2 }) => {
    return (
        <div style={style.container}>
            
            <div style={{color: 'black' }}>
            <center>
                <div><h1>{content}</h1></div>
                <div style={{ margin: '20px', color: '#5D5D5D' }}><h2>{content2}</h2></div>
                <div className="sub1pic" /></center>
            </div>
            
        </div>
    );
}

const style = {
    container: {
        width: "100%",
        height: "700px",
        margin: "0 auto",
        paddingTop: '80px',
        backgroundColor: '#ffffff'
    }
}

export default SubComponent