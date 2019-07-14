import React from 'react';
import './style.css';

const SubComponent = ({content, content2, img}) => {
    return(
        <div style={style.container} className="bottombanner">
            {/* <div style={{textAlign: 'center', color:'white'}}>
                <h1>{content}</h1>                       
            </div> */}
        </div>
    );
}

const style={
    container:{
        width:"100%",
        height:"280px",
        margin:"0 auto",        
        paddingTop: '80px',
        backgroundColor:'#8041D9'
    }
}

export default SubComponent