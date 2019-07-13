import React, { Component } from 'react';
import classNames from 'classnames';
import { withStyles } from '@material-ui/core';
import { SearchInput } from 'components';
import { useDispatch } from 'react-redux';

import styles from './styles';

const UsersToolbar = ({ classes, className }) => {
  const dispatch = useDispatch();
  const rootClassName = classNames(classes.root, className);

  return (
    <div className={rootClassName}>
      <div className={classes.row}>
        <span className={classes.spacer} />
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
