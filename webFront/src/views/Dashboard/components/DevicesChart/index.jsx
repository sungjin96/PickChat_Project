import React from 'react';
import { Doughnut } from 'react-chartjs-2';
import classNames from 'classnames';
import { Typography } from '@material-ui/core';
import { withStyles } from '@material-ui/core';
import {
  LaptopMac as LaptopMacIcon,
  TabletMac as TabletMacIcon
} from '@material-ui/icons';
import {
  Portlet,
  PortletHeader,
  PortletLabel,
  PortletContent
} from 'components';
import palette from 'theme/palette';
import { options } from './chart';
import styles from './styles';
import Axios from 'axios';

const DevicesChart = ({ classes, className, ...rest }) => {
  const rootClassName = classNames(classes.root, className);
  const [users, setUsers] = React.useState([]);
  const [counter, setCounter] = React.useState({
    total: 0,
    mCounter: 0,
    wCounter: 0
  });

  const Counter = () => {
    React.useEffect(() => {
      Axios.get('http://sungjin5891.cafe24.com/user/list_user').then(data => {
        setUsers(data.data);
        setCounter({
          ...counter,
          total: data.data.length
        });
      });
    }, []);

    React.useEffect(() => {
      const m = users.filter(data => data.gendername === '남');
      const w = users.filter(data => data.gendername === '여');

      setCounter({
        ...counter,
        mCounter: m.length,
        wCounter: w.length
      });
    }, [counter.total]);

    return [counter.mCounter, counter.wCounter];
  };

  return (
    <Portlet {...rest} className={rootClassName}>
      <PortletHeader noDivider>
        <PortletLabel title="성별 비율" />
      </PortletHeader>
      <PortletContent>
        <div className={classes.chartWrapper}>
          <Doughnut
            data={{
              datasets: [
                {
                  data: Counter(),
                  backgroundColor: [palette.primary.main, palette.danger.main],
                  borderWidth: 8,
                  borderColor: palette.common.white,
                  hoverBorderColor: palette.common.white
                }
              ],
              labels: ['남자', '여자']
            }}
            options={options}
          />
        </div>
        <div className={classes.stats}>
          <div className={classes.device}>
            <LaptopMacIcon className={classes.deviceIcon} />
            <Typography variant="body1">남자</Typography>
            <Typography style={{ color: palette.primary.main }} variant="h2">
              {Math.round((counter.mCounter / counter.total) * 100)}%
            </Typography>
          </div>
          <div className={classes.device}>
            <TabletMacIcon className={classes.deviceIcon} />
            <Typography variant="body1">여자</Typography>
            <Typography style={{ color: palette.danger.main }} variant="h2">
              {Math.round((counter.wCounter / counter.total) * 100)}%
            </Typography>
          </div>
        </div>
      </PortletContent>
    </Portlet>
  );
};

export default withStyles(styles)(DevicesChart);
