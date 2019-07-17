import React, { Component } from 'react';

// Externals
import PropTypes from 'prop-types';
import classNames from 'classnames';

// Material helpers
import { withStyles } from '@material-ui/core';

// Material components
import { Button, IconButton } from '@material-ui/core';

// Material icons
import {
  ArrowDownward as ArrowDownwardIcon,
  ArrowUpward as ArrowUpwardIcon,
  Delete as DeleteIcon
} from '@material-ui/icons';

// Shared components
import { DisplayMode, SearchInput } from 'components';
import { useDispatch } from 'react-redux';

// Component styles
import styles from './styles';

const UsersToolbar = ({ classes, className, selectedUsers }) => {
  const rootClassName = classNames(classes.root, className);
  const dispatch = useDispatch();
  return (
    <div className={rootClassName}>
      <div className={classes.row}>
        <span className={classes.spacer} />
        {selectedUsers.length > 0 && (
          <IconButton
            className={classes.deleteButton}
            onClick={this.handleDeleteUsers}>
            <DeleteIcon />
          </IconButton>
        )}
        <Button
          className={classes.importButton}
          size="small"
          variant="outlined">
          수정
        </Button>
        <Button
          className={classes.exportButton}
          size="small"
          variant="outlined">
          삭제
        </Button>
        <Button color="primary" size="small" variant="outlined">
          글쓰기
        </Button>
      </div>
      <div className={classes.row}>
        <SearchInput
          className={classes.searchInput}
          placeholder="Search user"
          onChange={e =>
            dispatch({
              type: 'Faq/SEARCH_INPUT',
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
