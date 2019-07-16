import React from 'react';
import CreateModal from '../../views/NoticeList/components/createModal';
import { Button } from '@material-ui/core';
import { useDispatch } from 'react-redux';

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
      공지추가
    </Button>
    </center>
  );
};

export default NoticeModalContainer;
