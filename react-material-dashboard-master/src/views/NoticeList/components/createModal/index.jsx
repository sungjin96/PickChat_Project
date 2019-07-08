import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';

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

const createModal = () => {
  const classes = Style();

  return (
    <div
      style={{
        backgroundColor: '#ffffff',
        position: 'absolute',
        outline: 'none',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: '60rem',
        height: '50rem'
      }}>
      <div
        style={{
          width: '90%',
          textAlign: 'center',
          height: '20%',
          margin: 'auto'
        }}>
        공지사항 입력
      </div>
      <TextField
        label="제목"
        multiline
        rows="2"
        className={classes.textField}
        margin="normal"
        variant="outlined"
        style={{ width: '98%' }}
      />
      <TextField
        label="내용"
        multiline
        rows="2"
        className={classes.textField}
        margin="normal"
        variant="outlined"
        style={{ width: '98%' }}
      />
    </div>
  );
};

export default createModal;
