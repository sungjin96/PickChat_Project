import React from 'react';
import { white } from 'ansi-colors';

const SubComponent = ({content, content2, img}) => {
    return(
        <div style={style.container}>
            <div style={{textAlign: 'center', color:'white'}}>
                <h1>{content}</h1>                       
            </div>
        </div>
    );
}

const style={
    container:{
        width:"2000px",
        height:"150px",
        margin:"0 auto",        
        paddingTop: '80px',
        backgroundColor:'#8041D9'
    },   
    img:{
        margin:"0 auto",
        backgroundColor: '#ccc', width:'400px', height: '400px'
    }
}

export default SubComponent