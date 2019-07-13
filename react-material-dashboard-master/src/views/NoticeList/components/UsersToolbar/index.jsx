import React, { Component } from 'react';
import { useDispatch } from 'react-redux';
import classNames from 'classnames';

import CreateModal from '../createModal';

import { withStyles } from '@material-ui/core';
import Modal from '@material-ui/core/Modal';
import { Button } from '@material-ui/core';

import { SearchInput } from 'components';

// Component styles
import styles from './styles';

const UsersToolbar = ({ classes, className, selectedUsers }) => {
  const [state, setState] = React.useState({
    setOpen: false
  });
  const dispatch = useDispatch();
  const handleOpen = () => {
    setState({ setOpen: true });
  };

  const handleClose = () => {
    setState({ setOpen: false });
  };

  const rootClassName = classNames(classes.root, className);

  return (
    <div className={rootClassName}>
      <div className={classes.row}>
        <span className={classes.spacer} />
        <Button
          color="primary"
          size="small"
          variant="outlined"
          onClick={handleOpen}>
          글쓰기
        </Button>
        <Modal
          aria-labelledby="simple-modal-title"
          aria-describedby="simple-modal-description"
          open={state.setOpen}
          onClose={handleClose}>
          <div style={{}}>
            <CreateModal />
          </div>
        </Modal>
      </div>
      <div className={classes.row}>
        <SearchInput
          className={classes.searchInput}
          placeholder="Search user"
          onChange={e =>
            dispatch({
              type: 'SEARCH_INPUT',
              payload: {
                input: e.target.value
              }
            })
          }
        />
        <span className={classes.spacer} />
      </div>
    </div>
  );
};

export default withStyles(styles)(UsersToolbar);
