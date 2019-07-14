import React from 'react';
import './style.css';

const SubComponent = ({ content, content2 }) => {
    return (
        <div style={style.container}>
            <div style={{ textAlign: 'center'}}>
                <div style={{ margin: '15px', color: 'black'  }}><h1>{content}</h1></div>
                <div style={{ margin: '15px', color: '#5D5D5D' }}><h2>{content2}</h2></div>       
                    <div style={{marginLeft: '37%'}}>
                         <div className="item1pic"/>
                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         <div className="item2pic"/>
                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         <div className="item3pic"/>
                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         <div className="item4pic"/>                                
                    </div>                                              
            </div>         
        </div>
    );
}

const style = {
    container: {
        width: "2000px",
        height: "600px",
        margin: "0 auto",
        paddingTop: '80px',
        backgroundColor: '#ffffff'
    }
}

export default SubComponent