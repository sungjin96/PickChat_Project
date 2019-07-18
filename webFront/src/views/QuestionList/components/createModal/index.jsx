import React from 'react';
import TextField from '@material-ui/core/TextField';
import QuestionContainer from '../../../../containers/QuestionContainer';


const CreateModal = ({ qno }) => {
  const [value, setValue] = React.useState('');

  return (
    <div
      style={{
        backgroundColor: '#ffffff',
        position: 'absolute',
        outline: 'none',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: '40rem',
        height: '30rem' }}>
        <p
        style={{
          width: '100%',
          textAlign: 'center',
          height: '10%',
          margin: 'auto',
          fontWeight:'bold',
          fontSize:'20px'
        }}>
          <br/>
        COMMENT
      </p>
      <center>
      <br/>
      <TextField      
        label="글 번호"
        multiline
        rows="1"
        margin="normal"
        variant="outlined"
        style={{ width: '90%', textAlign:'center' }}
        value={qno}/>
      
      <TextField
        label="내용"
        multiline
        rows="7"
        margin="normal"
        variant="outlined"
        style={{ width: '90%', textAlign:'center'  }}
        value={value}
        onChange={e => setValue(e.target.value)}   
      />
      </center> 
      <QuestionContainer qno={qno} content={value} />
    </div>
  );
};

export default CreateModal;



