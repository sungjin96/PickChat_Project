import React, { Component } from 'react';

import classNames from 'classnames';

// Material components
import { Button } from '@material-ui/core';
import CreateModal from '../createModal';
import Modal from '@material-ui/core/Modal';
import QuestionContainer from 'containers/QuestionContainer';

class Btn extends Component {
  state = {
    setOpen: false
  };

  handleOpen = () => {
    this.setState({ setOpen: true });
  };

  handleClose = () => {
    this.setState({ setOpen: false });
  };

  render() {
    const { qno } = this.props;

    return (
      <div>
        <Button
          color="primary"
          size="small"
          variant="outlined"
          onClick={this.handleOpen}>
          COMMENT
        </Button>
        <Modal
          aria-labelledby="simple-modal-title"
          aria-describedby="simple-modal-description"
          open={this.state.setOpen}
          onClose={this.handleClose}>
          <div style={{}}>
            <CreateModal qno={qno} />
          </div>
        </Modal>
      </div>
    );
  }
}

export default Btn;
