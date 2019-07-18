import React from 'react';
import CreateModal from '../../views/NoticeList/components/createModal';
import { useDispatch } from 'react-redux';
import { Button } from '@material-ui/core';

const NoticeModalContainer = ({ title, content }) => {
  const dispatch = useDispatch();

  return (
    <center>
    <Button
      onClick={() =>
        dispatch({
          type: 'INSERT',
          payload: { title: title, content: content }
        })
      }>
      입력완료
    </Button>
    </center>
  );
};

export default NoticeModalContainer;
