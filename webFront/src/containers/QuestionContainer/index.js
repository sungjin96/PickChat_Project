import React from 'react';
import { Button } from '@material-ui/core';

import { useDispatch } from 'react-redux';

const QuestionContainer = ({ qno, content }) => {
  const dispatch = useDispatch();
  return (
    <center>
    <Button
      onClick={() =>
        dispatch({
          type: 'COMMENT',
          payload: {
            qno: qno,
            content: content
          }
        })
      }>
      Add
    </Button>
    </center>
  );
};

export default QuestionContainer;
