import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Chip from '@material-ui/core/Chip';
import DeleteBtn from '../../../../containers/NoticeModalContainer/DeleteBtn';

// Externals
import classNames from 'classnames';
import PropTypes from 'prop-types';
import moment from 'moment';
import PerfectScrollbar from 'react-perfect-scrollbar';

// Material helpers
import { withStyles, Button } from '@material-ui/core';

// Material components
import {
  Avatar,
  Checkbox,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography,
  TablePagination
} from '@material-ui/core';

// Shared helpers
import { getInitials } from 'helpers';

// Shared components
import { Portlet, PortletContent } from 'components';

// Component styles
import styles from './styles';
import Axios from 'axios';
import { useSelector, useDispatch } from 'react-redux';

const UsersTable = ({ classes, className }) => {
  const [state, setState] = useState({
    client: []
  });

  const input = useSelector(state => state.PickModule.input, []);
  const dispatch = useDispatch();

  useEffect(() => {
    Axios.get('http://sungjin5891.cafe24.com/board/bbslist').then(data =>
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
                <TableCell align="left">작성자</TableCell>
                <TableCell align="left">제목</TableCell>
                <TableCell align="left">내용</TableCell>
                <TableCell align="left">태그</TableCell>
                <TableCell align="left">등록일</TableCell>
                <TableCell align="left">삭제</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {state.client !== undefined
                ? state.client
                    .filter(data => {
                      return data.title.indexOf(input) > -1;
                    })
                    .map(user => (
                      <TableRow
                        className={classes.tableRow}
                        hover
                        key={user.bno}>
                        <TableCell className={classes.tableCell}>
                          <div className={classes.tableCellInner}>
                            <Avatar
                              className={classes.avatar}
                              src={user.imgpath}>
                              {getInitials(
                                user.writer !== null ? user.writer : ''
                              )}
                            </Avatar>
                            <Link to="#">
                              <Typography
                                className={classes.nameText}
                                variant="body1"
                                style={{ fontSize: '1rem' }}>
                                {user.writer}
                              </Typography>
                            </Link>
                          </div>
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {user.title}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {user.content}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {user.tagword.map((data, index) => (
                            <Chip
                              key={index}
                              label={'#' + data}
                              variant="outlined"
                            />
                          ))}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {moment(user.regdate).format('DD/MM/YYYY')}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          <Button
                            onClick={() =>
                              dispatch({
                                type: 'REMOVE',
                                payload: {
                                  bno: user.bno
                                }
                              })
                            }>
                            DELETE
                          </Button>
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
