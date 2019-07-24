import React, { Component } from 'react';
import { Button } from '@material-ui/core';
import CreateModal from '../createModal';
import Modal from '@material-ui/core/Modal';
import { useDispatch, useSelector } from 'react-redux';

const Btn = React.memo(({ qno }) => {
  const [modalState, updateCheck, no] = useSelector(
    state => [
      state.QuestionModule.modalState,
      state.QuestionModule.updateCheck,
      state.QuestionModule.no
    ],
    []
  );

  React.useEffect(() => {
    setState({ setOpen: modalState });
    return () => {};
  }, [updateCheck]);

  const [state, setState] = React.useState({
    setOpen: modalState
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
      <Modal
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description"
        open={state.setOpen}
        onClose={handleClose}>
        <div style={{}}>
          <CreateModal qno={no} />
        </div>
      </Modal>
    </div>
  );
});

export default Btn;
