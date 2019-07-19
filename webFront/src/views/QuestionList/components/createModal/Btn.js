import React, { Component } from 'react';
import { Button } from '@material-ui/core';
import CreateModal from '../createModal';
import Modal from '@material-ui/core/Modal';

import { useSelector, useDispatch } from 'react-redux';

const Btn = React.memo(({ qno }) => {
  const [CloseState, UpdateCheak] = useSelector(
    state => [
      state.QuestionModule.CloseState,
      state.QuestionModule.UpdateCheak
    ],
    []
  );

  React.useEffect(() => {
    return () => {
      setState({ setOpen: CloseState });
    };
  }, [UpdateCheak]);

  const [state, setState] = React.useState({
    setOpen: CloseState
  });

  const handleOpen = () => {
    setState({ setOpen: true });
  };

  const handleClose = () => {
    setState({ setOpen: false });
  };

  const dispatch = useDispatch();

  return (
    <div>
      <Button
        size="small"
        onClick={() => setState({ setOpen: !state.setOpen })}>
        COMMENT
      </Button>
      <Modal
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description"
        open={state.setOpen}
        onClose={handleClose}>
        <div style={{}}>
          <CreateModal qno={qno} />
        </div>
      </Modal>
    </div>
  );
});

export default Btn;
