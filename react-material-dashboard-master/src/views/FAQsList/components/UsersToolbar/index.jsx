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

// Component styles
import styles from './styles';

class UsersToolbar extends Component {
  render() {
    const { classes, className, selectedUsers } = this.props;

    const rootClassName = classNames(classes.root, className);

    return (
      <div className={rootClassName}>
        <div className={classes.row}>
          <span className={classes.spacer} />
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
      </div>
    );
  }
}

UsersToolbar.propTypes = {
  className: PropTypes.string,
  classes: PropTypes.object.isRequired,
  selectedUsers: PropTypes.array
};

UsersToolbar.defaultProps = {
  selectedUsers: []
};

export default withStyles(styles)(UsersToolbar);
