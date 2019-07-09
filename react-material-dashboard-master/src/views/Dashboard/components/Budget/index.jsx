import React, { Component } from 'react';

// Externals
import classNames from 'classnames';
import PropTypes from 'prop-types';

// Material helpers
import { withStyles } from '@material-ui/core';

// Material components
import { Typography } from '@material-ui/core';

// Material icons
import {
  ArrowDownward as ArrowDownwardIcon,
  Money as MoneyIcon
} from '@material-ui/icons';

// Shared components
import { Paper } from 'components';

// Component styles
import styles from './styles';
import axios from 'axios';

class Budget extends Component {
  state = {
    total: 0
  };

  componentDidMount() {
    axios.get('http://192.168.0.104:5000/board/bbslist').then(data => {
      const total = data.data.length;

      this.setState({
        total
      });
    });
  }

  render() {
    const { classes, className, ...rest } = this.props;

    const rootClassName = classNames(classes.root, className);

    return (
      <Paper {...rest} className={rootClassName}>
        <div className={classes.content}>
          <div className={classes.details}>
            <Typography className={classes.title} variant="body2">
              게시글수
            </Typography>
            <Typography className={classes.value} variant="h3">
              {this.state.total}
            </Typography>
          </div>
          <div className={classes.iconWrapper}>
            <MoneyIcon className={classes.icon} />
          </div>
        </div>
        <div className={classes.footer} />
      </Paper>
    );
  }
}

Budget.propTypes = {
  className: PropTypes.string,
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(Budget);
