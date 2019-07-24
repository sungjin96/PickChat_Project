import React, { Component } from 'react';
import { Button } from '@material-ui/core';
import CreateModal from '../createModal';
import Modal from '@material-ui/core/Modal';

const Btn = React.memo(({ qno }) => {
  const [state, setState] = React.useState({
    setOpen: false
  });

  const handleOpen = () => {
    setState({ setOpen: true });
  };

  const handleClose = () => {
    setState({ setOpen: false });
  };
  return (
    <div>
      <Button
        size="small"
        onClick={handleOpen}>
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
