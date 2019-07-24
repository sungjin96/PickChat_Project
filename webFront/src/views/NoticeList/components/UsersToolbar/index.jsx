import React from 'react';
import classNames from 'classnames';
import { withStyles } from '@material-ui/core';
import CreateModal from '../createModal';
import Modal from '@material-ui/core/Modal';
import { Button } from '@material-ui/core';
import { SearchInput } from 'components';
import { useDispatch, useSelector } from 'react-redux';

// Component styles
import styles from './styles';

const UsersToolbar = ({ classes, className }) => {
  const modalState = useSelector(state => state.NoticeModule.modalState);

  React.useEffect(() => {
    setState({ setOpen: modalState });
    return () => {};
  }, [modalState]);

  const [state, setState] = React.useState({
    setOpen: modalState
  });
  const dispatch = useDispatch();

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
          onClick={() => dispatch({ type: 'Notice/MODAL_CHECK' })}>
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
