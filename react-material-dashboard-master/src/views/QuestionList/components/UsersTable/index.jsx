import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Btn from '../createModal/Btn';
import classNames from 'classnames';
import moment from 'moment';
import PerfectScrollbar from 'react-perfect-scrollbar';
import { withStyles } from '@material-ui/core';
import { useSelector } from 'react-redux';

import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography
} from '@material-ui/core';

// Shared components
import { Portlet, PortletContent } from 'components';

// Component styles
import styles from './styles';
import Axios from 'axios';

const UsersTable = ({ classes, className }) => {
  const [state, setState] = React.useState({
    selectedUsers: [],
    rowsPerPage: 10,
    page: 0,
    client: []
  });
  const input = useSelector(state => state.QuestionModule.input);
  React.useEffect(() => {
    Axios.get('http://sungjin5891.cafe24.com/question/list').then(data =>
      setState({ client: data.data })
    );
  }, [state.client]);

  const rootClassName = classNames(classes.root, className);

  return (
    <Portlet className={rootClassName}>
      <PortletContent noPadding>
        <PerfectScrollbar>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell align="left">아이디</TableCell>
                <TableCell align="left">제목</TableCell>
                <TableCell align="left">내용</TableCell>
                <TableCell align="left">답글</TableCell>
                <TableCell align="left">등록일</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {state.client !== undefined
                ? state.client
                    .filter(data => {
                      return data.qtitle.indexOf(input) > -1;
                    })
                    .map(user => (
                      <TableRow
                        className={classes.tableRow}
                        hover
                        key={user.qno}>
                        <TableCell className={classes.tableCell}>
                          <div className={classes.tableCellInner}>
                            <Link to="#">
                              <Typography
                                className={classes.nameText}
                                variant="body1"
                                style={{ fontSize: '1rem' }}>
                                {user.qwriter}
                              </Typography>
                            </Link>
                          </div>
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {user.qtitle}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {user.qcontent}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {user.qccontent}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {moment(user.qregdate).format('DD/MM/YYYY')}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          <Btn qno={user.qno} />
                        </TableCell>
                      </TableRow>
                    ))
                : ''}
            </TableBody>
          </Table>
        </PerfectScrollbar>
      </PortletContent>
    </Portlet>
  );
};

export default withStyles(styles)(UsersTable);
