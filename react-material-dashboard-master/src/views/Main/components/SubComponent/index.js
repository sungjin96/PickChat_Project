import React from 'react';
import { white } from 'ansi-colors';

const SubComponent = ({content, content2, img}) => {
    return(
        <div style={style.container}>                                      
            <div style={{textAlign: 'center', color:'white'}}>
                <h1 style={{margin: '10px'}}>{content}</h1>
                <h2  style={{margin: '10px'}}>{content2}</h2>
            </div>

            <div style={style.img}/>           
        </div>
    );
}

const style={
    container:{
        width:"1200px",
        height:"600px",
        margin:"0 auto",
        backgroundColor:"#FFE3FF",
        paddingTop: '80px'
    },
    img:{
        margin:"0 auto",
        backgroundColor: '#ccc', width:'600px', height: '400px'
    }
}

export default SubComponent