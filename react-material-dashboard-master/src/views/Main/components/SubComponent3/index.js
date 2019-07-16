import React from 'react';
import img1 from '../../../../assets/img/chat.png'
import img2 from '../../../../assets/img/listen.png'
import img3 from '../../../../assets/img/search.png'
import img4 from '../../../../assets/img/trust.png'
import './style.css';

const SubComponent = ({ content, content2 }) => {
    return (
        <div style={style.container}>
            <br></br><br></br>
            <div><h1>{content}</h1></div>
            <br></br><br></br><br></br>
            <div>
                <span><img className='img1' src={img1} alt='이미지가 없을때 뜨는 메세지' style={{width:'110px', height:'100px'}}></img></span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span><img className='img1' src={img2} alt='이미지가 없을때 뜨는 메세지' style={{width:'110px', height:'100px'}}></img></span>
            </div>
            <br/><br/><br/>
           
            <div>
                <span><img className='img1' src={img3} alt='이미지가 없을때 뜨는 메세지' style={{width:'110px', height:'100px'}}></img></span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span><img className='img1' src={img4} alt='이미지가 없을때 뜨는 메세지' style={{width:'110px', height:'100px'}}></img></span>
            </div>
        </div>
    );
}

const style = {
    container: {
        width: "100%",
        height: "400px",
        margin: "0 auto",
        textAlign: 'center',
        backgroundColor: '#ffffff'
    }
}

export default SubComponent