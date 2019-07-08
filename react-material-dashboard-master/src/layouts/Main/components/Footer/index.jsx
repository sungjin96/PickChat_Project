import React, { Component } from 'react';

// Externals
import PropTypes from 'prop-types';
import classNames from 'classnames';

// Material helpers
import { withStyles } from '@material-ui/core';

// Material components
import { Divider, Typography } from '@material-ui/core';

// Component styles
const styles = theme => ({
  root: {
    padding: theme.spacing.unit * 4
  },
  company: {
    marginTop: theme.spacing.unit * 2,
    marginBottom: theme.spacing.unit * 0.5
  }
});

class Footer extends Component {
  render() {
    const { classes, className } = this.props;

    const rootClassName = classNames(classes.root, className);

    return (
      <div className={rootClassName}>
        <Divider />
        <Typography
          className={classes.company}
          variant="body1"
        >
          &copy; Devias Io. 2019
        </Typography>
        <Typography variant="caption">
          <center>
            상호명 : 픽챗 사업자번호 : 111-11-12345 <br />통신판매신고번호 : 2019-가산동-1387호 픽챗 상표권 : 제 41-03069809 호
        <br />소재지 : 서울특별시 금천구 가산디지털2로 123 월드메르디앙2차 <br />대표자 : 상진영 고객센터 : jinyoung@pickchat.com
        <br />Copyrights(c) PickChat. All rights reserved.</center>
        </Typography>
      </div>
    );
  }
}

Footer.propTypes = {
  className: PropTypes.string,
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(Footer);
