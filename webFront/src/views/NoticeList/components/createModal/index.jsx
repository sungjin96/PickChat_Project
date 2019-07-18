import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import NoticeModalContainer from '../../../../containers/NoticeModalContainer';
import { bold } from 'ansi-colors';

const Style = makeStyles(theme => ({
  container: {
    display: 'flex',
    flexWrap: 'wrap'
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1)
  },
  dense: {
    marginTop: theme.spacing(2)
  },
  menu: {
    width: 200
  }
}));

const CreateModal = ({ onInsert }) => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

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
        height: '30rem'
      }}>
        <br/><br/>
      <p className="taeyong"
        style={{
          width: '100%',
          textAlign: 'center',
          height: '10%',
          margin: 'auto',
          fontWeight:'bold',
          fontSize:'20px'
        }}>
        공지사항 입력
      </p>
      <center>
      <TextField
        label="제목"
        multiline
        rows="3"
        margin="normal"
        variant="outlined"
        style={{ width: '90%', textAlign:'center' }}
        value={title}
        onChange={e => setTitle(e.target.value)}
      />
      <TextField
        label="내용"
        multiline
        rows="7"
        margin="normal"
        variant="outlined"
        style={{ width: '90%', textAlign:'center'  }}
        value={content}
        onChange={e => setContent(e.target.value)}
      />
      </center>

      <NoticeModalContainer title={title} content={content} />
    </div>
  );
};


export default CreateModal;
