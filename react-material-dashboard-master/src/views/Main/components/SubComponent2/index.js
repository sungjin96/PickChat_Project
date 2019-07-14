import React from 'react';
import './style.css';

const SubComponent = ({content, content2, img}) => {
    return(
        <div style={style.container}>
            <center>
                
                <div className="mainlogo"/>
                           
            </center>
        </div>           
    );
}

const style={
    container:{
        width:"100%",
        height:"300px",
        margin:"0 auto",
        // paddingTop: '80px',
        backgroundColor:'#D4F4FA'
    }
}

export default SubComponent