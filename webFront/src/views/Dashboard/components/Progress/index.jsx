import React from 'react';
import classNames from 'classnames';
import { withStyles } from '@material-ui/core';
import { Typography, LinearProgress } from '@material-ui/core';
import { InsertChartOutlined as InsertChartIcon } from '@material-ui/icons';
import { Paper } from 'components';
import Axios from 'axios';

import styles from './styles';

const Progress = ({ classes, className, ...rest }) => {
  const [profit, setProfit] = React.useState(0);

  React.useEffect(() => {
    Axios.get('http://sungjin5891.cafe24.com/user/total_userprofit').then(
      data => setProfit(data.data)
    );
  }, []);
  const getProgress = () => {
    const setProgress = 50000000;
    const result = (profit / setProgress) * 100;
    return result;
  };

  const rootClassName = classNames(classes.root, className);
  return (
    <Paper {...rest} className={rootClassName}>
      <div className={classes.content}>
        <div className={classes.details}>
          <Typography className={classes.title} variant="body2">
            PROGRESS
          </Typography>
          <Typography className={classes.value} variant="h3">
            {getProgress()}%
          </Typography>
        </div>
        <div className={classes.iconWrapper}>
          <InsertChartIcon className={classes.icon} />
        </div>
      </div>
      <div className={classes.footer}>
        <LinearProgress value={getProgress()} variant="determinate" />
      </div>
    </Paper>
  );
};

export default withStyles(styles)(Progress);
