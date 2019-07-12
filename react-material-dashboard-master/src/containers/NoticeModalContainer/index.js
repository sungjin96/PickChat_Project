import React from 'react';
import CreateModal from '../../views/NoticeList/components/createModal';
import { useDispatch } from 'react-redux';

const NoticeModalContainer = ({ title, content }) => {
  const dispatch = useDispatch();

  return (
    <button
      onClick={() =>
        dispatch({
          type: 'INSERT',
          payload: { title: title, content: content }
        })
      }>
      Insert
    </button>
  );
};

export default NoticeModalContainer;
