import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import classNames from 'classnames';
import moment from 'moment';
import PerfectScrollbar from 'react-perfect-scrollbar';
import { withStyles } from '@material-ui/core';
import { useSelector } from 'react-redux';
import DeleteBtn from '../../../../containers/NoticeModalContainer/DeleteBtn';

// Material components
import {
  Avatar,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography
} from '@material-ui/core';

// Shared helpers
import { getInitials } from 'helpers';

// Shared components
import { Portlet, PortletContent } from 'components';

// Component styles
import styles from './styles';
import Axios from 'axios';

const UsersTable = ({ classes, className }) => {
  const [state, setState] = useState({
    selectedUsers: [],
    rowsPerPage: 10,
    page: 0,
    client: [],
    total: 0
  });
  const input = useSelector(state => state.UserModule.input, []);
  useEffect(() => {
    Axios.get('http://sungjin5891.cafe24.com/user/list_user').then(data =>
      setState({ client: data.data })
    );
  }, []);

  const rootClassName = classNames(classes.root, className);

  return (
    <Portlet className={rootClassName}>
      <PortletContent noPadding>
        <PerfectScrollbar>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell align="left">이름</TableCell>
                <TableCell align="left">아이디</TableCell>
                <TableCell align="left">성별</TableCell>
                <TableCell align="left">나이</TableCell>
                <TableCell align="left">등록일</TableCell>
                <TableCell align="left">삭제</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {state.client !== undefined
                ? state.client
                    .filter(data => {
                      return data.username.indexOf(input) > -1;
                    })
                    .map(user => (
                      <TableRow
                        className={classes.tableRow}
                        hover
                        key={user.userid}>
                        <TableCell className={classes.tableCell}>
                          <div className={classes.tableCellInner}>
                            <Avatar
                              className={classes.avatar}
                              src={user.soloimg}>
                              {getInitials(user.username)}
                            </Avatar>
                            <Link to={`/user/${user.userid}`}>
                              <Typography
                                className={classes.nameText}
                                variant="body1"
                                style={{ fontSize: '0.9rem' }}>
                                {user.username}
                              </Typography>
                            </Link>
                          </div>
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '0.9rem' }}>
                          {user.userid}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '0.9rem' }}>
                          {user.gendername}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '0.9rem' }}>
                          {user.userage}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '0.9rem' }}>
                          {moment(user.regdate).format('DD/MM/YYYY')}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '0.9rem' }}>
                          <DeleteBtn id={user.nno} />
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
