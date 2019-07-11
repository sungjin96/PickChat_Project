import React from 'react';
import { Button } from '@material-ui/core';

import { useDispatch } from 'react-redux';

const QuestionContainer = ({ qno, content }) => {
  const dispatch = useDispatch();
  return (
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
      COMMENT
    </Button>
  );
};

export default QuestionContainer;
