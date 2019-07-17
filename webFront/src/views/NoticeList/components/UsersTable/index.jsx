import React, { Component } from 'react';
import { useSelector } from 'react-redux';
import classNames from 'classnames';
import moment from 'moment';
import PerfectScrollbar from 'react-perfect-scrollbar';
import { withStyles } from '@material-ui/core';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography
} from '@material-ui/core';
import DeleteBtn from '../../../../containers/NoticeModalContainer/DeleteBtn';

// Shared components
import { Portlet, PortletContent } from 'components';

// Component styles
import styles from './styles';
import Axios from 'axios';

const UsersTable = React.memo(({ classes, className }) => {
  const [state, setState] = React.useState({
    client: []
  });

  const input = useSelector(state => state.NoticeModule.input);

  React.useEffect(() => {
    Axios.get('http://sungjin5891.cafe24.com/notice/list').then(data =>
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
                <TableCell align="left">번호</TableCell>
                <TableCell align="left">제목</TableCell>
                <TableCell align="left">내용</TableCell>
                <TableCell align="left">등록일</TableCell>
                <TableCell align="left">삭제</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {state.client !== undefined
                ? state.client.map(user => (
                    <TableRow className={classes.tableRow} hover key={user.nno}>
                      <TableCell className={classes.tableCell}>
                        <div className={classes.tableCellInner}>
                          <Typography
                            className={classes.nameText}
                            variant="body1"
                            style={{ fontSize: '1rem' }}>
                            {user.nno}
                          </Typography>
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
                        {moment(user.regdate).format('DD/MM/YYYY')}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
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
});

export default withStyles(styles)(UsersTable);
