import React from 'react';
import classNames from 'classnames';
import { useDispatch } from 'react-redux';

import Modal from '@material-ui/core/Modal';
import { withStyles } from '@material-ui/core';

import CreateModal from '../createModal';

import { SearchInput } from 'components';

import styles from './styles';

const UsersToolbar = ({ classes, className, selectedUsers }) => {
  const [state, setState] = React.useState({
    setOpen: false
  });
  const dispatch = useDispatch();
  const rootClassName = classNames(classes.root, className);
  return (
    <div className={rootClassName}>
      <div className={classes.row}>
        <span className={classes.spacer} />
        <Modal
          aria-labelledby="simple-modal-title"
          aria-describedby="simple-modal-description"
          open={state.setOpen}>
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
