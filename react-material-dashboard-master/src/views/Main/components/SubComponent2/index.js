import React from 'react';


const SubComponent = ({content, content2, img}) => {
    return(
        <div style={style.container}>
            <hr></hr>
            <div style={{marginLeft:'20%', color:'Purple', fontSize:'30px'}}>                
            <br></br><br></br><br></br><br></br><br></br><br></br><br></br>
            <h1>PickChat</h1><br></br><br></br><br></br><br></br><br></br><br></br>
            <h3>Everyday, we do our best for our members.</h3>           
            </div>
            <br></br><br></br><br></br><br></br><br></br>
            <hr></hr>
        </div>
    );
}

const style={
    container:{
        width:"100%",
        height:"300px",
        margin:"0 auto",
        // paddingTop: '80px',
        backgroundColor:'#ffffff'
    }
}

export default SubComponent