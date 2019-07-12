import React from 'react';
import './style.css';

const SubComponent = ({ content, content2 }) => {
    return (
        <div style={style.container}>
            <div style={{ textAlign: 'center', color: 'black' }}>
                <div style={{ margin: '15px' }}><h1>{content}</h1></div>
                <div style={{ margin: '15px' }}><h2>{content2}</h2></div>
                <center><div className="sub1pic" /></center>
            </div>
        </div>
    );
}

const style = {
    container: {
        width: "2000px",
        height: "600px",
        margin: "0 auto",
        paddingTop: '80px'
    }
}

export default SubComponent