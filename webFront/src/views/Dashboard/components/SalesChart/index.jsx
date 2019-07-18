import React from 'react';
import classNames from 'classnames';
import { Bar } from 'react-chartjs-2';
import { withStyles } from '@material-ui/core';
import {
  Portlet,
  PortletHeader,
  PortletLabel,
  PortletContent
} from 'components';
import { data, options } from './chart';
import styles from './styles';
import palette from 'theme/palette';
import Axios from 'axios';

const SalesChart = ({ classes, className, ...rest }) => {
  const rootClassName = classNames(classes.root, className);
  const [users, setUsers] = React.useState([]);
  const [counter, setCounter] = React.useState({
    total: 0,
    mCounter10: 0,
    wCounter10: 0,
    mCounter20: 0,
    wCounter20: 0,
    mCounter30: 0,
    wCounter30: 0,
    mCounter40: 0,
    wCounter40: 0
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
      const m10 = users.filter(
        data => data.gendername === '남' && data.userage < 20
      );
      const w10 = users.filter(
        data => data.gendername === '여' && data.userage < 20
      );
      const m20 = users.filter(
        data =>
          data.gendername === '남' && data.userage < 30 && data.userage >= 20
      );
      const w20 = users.filter(
        data =>
          data.gendername === '여' && data.userage < 30 && data.userage >= 20
      );
      const m30 = users.filter(
        data =>
          data.gendername === '남' && data.userage < 40 && data.userage >= 30
      );
      const w30 = users.filter(
        data =>
          data.gendername === '여' && data.userage < 40 && data.userage >= 30
      );
      const m40 = users.filter(
        data =>
          data.gendername === '남' && data.userage < 50 && data.userage >= 40
      );
      const w40 = users.filter(
        data =>
          data.gendername === '여' && data.userage < 50 && data.userage >= 40
      );

      setCounter({
        ...counter,
        mCounter10: m10.length,
        wCounter10: w10.length,
        mCounter20: m20.length,
        wCounter20: w20.length,
        mCounter30: m30.length,
        wCounter30: w30.length,
        mCounter40: m40.length,
        wCounter40: w40.length
      });
    }, [counter.total]);

    return [
      counter.mCounter10,
      counter.mCounter20,
      counter.mCounter30,
      counter.mCounter40
    ];
  };

  return (
    <Portlet {...rest} className={rootClassName}>
      <PortletHeader noDivider>
        <PortletLabel title="연령대" />
      </PortletHeader>
      <PortletContent>
        <div className={classes.chartWrapper}>
          <Bar
            data={{
              labels: ['10대', '20대', '30대', '40대'],

              datasets: [
                {
                  label: '남',
                  backgroundColor: palette.primary.main,
                  data: Counter()
                },
                {
                  label: '여',
                  backgroundColor: palette.danger.main,
                  data: [
                    counter.wCounter10,
                    counter.wCounter20,
                    counter.wCounter30,
                    counter.wCounter40
                  ]
                }
              ]
            }}
            options={options}
          />
        </div>
      </PortletContent>
    </Portlet>
  );
};

export default withStyles(styles)(SalesChart);
