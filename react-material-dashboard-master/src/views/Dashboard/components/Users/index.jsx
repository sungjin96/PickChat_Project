import React, { Component } from 'react';

// Externals
import classNames from 'classnames';
import PropTypes from 'prop-types';

// Material helpers
import { withStyles } from '@material-ui/core';

// Material components
import { Typography } from '@material-ui/core';
import CircularProgress from '@material-ui/core/CircularProgress';

// Material icons
import {
  ArrowUpward as ArrowUpwardIcon,
  PeopleOutlined as PeopleIcon
} from '@material-ui/icons';

// Shared components
import { Paper } from 'components';

// Component styles
import styles from './styles';
import axios from 'axios';

class Users extends Component {
  state = {
    userTotal: 0
  };

  async componentDidMount() {
    let total;
    await axios
      .get('http://192.168.0.26/user/list_user')
      .then(data => (total = data.data.length));
    this.setState({ userTotal: total });
  }

  render() {
    const { classes, className, ...rest } = this.props;
    const { userTotal } = this.state;

    const rootClassName = classNames(classes.root, className);

    return (
      <Paper {...rest} className={rootClassName}>
        <div className={classes.content}>
          <div className={classes.details}>
            <Typography className={classes.title} variant="body2">
              TOTAL USERS
            </Typography>
            <Typography className={classes.value} variant="h3">
              {userTotal === 0 ? <CircularProgress /> : userTotal}
            </Typography>
          </div>
          <div className={classes.iconWrapper}>
            <PeopleIcon className={classes.icon} />
          </div>
        </div>
      </Paper>
    );
  }
}

Users.propTypes = {
  className: PropTypes.string,
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(Users);
