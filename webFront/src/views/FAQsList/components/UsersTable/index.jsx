import React from 'react';
import Faq from '../../../../data/Faq.js';
import classNames from 'classnames';
import moment from 'moment';
import PerfectScrollbar from 'react-perfect-scrollbar';
import { withStyles } from '@material-ui/core';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow
} from '@material-ui/core';

import DeleteBtn from '../../../../containers/NoticeModalContainer/DeleteBtn';

import { Portlet, PortletContent } from 'components';
import styles from './styles';
import { useSelector } from 'react-redux';

const UsersTable = ({ classes, className, users }) => {
  const rootClassName = classNames(classes.root, className);
  const input = useSelector(state => state.FaqModule.input);

  return (
    <Portlet className={rootClassName}>
      <PortletContent noPadding>
        <PerfectScrollbar>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell align="left" style={{ fontSize: '1.2rem', fontWeight: 'bold'  }}>
                  제목
                </TableCell>
                <TableCell align="left" style={{ fontSize: '1.2rem', fontWeight: 'bold'  }}>
                  내용
                </TableCell>
                <TableCell align="left" style={{ fontSize: '1.2rem', fontWeight: 'bold'  }}>
                  등록일
                </TableCell>
                <TableCell align="left" style={{ fontSize: '1.2rem', fontWeight: 'bold'  }}>
                  삭제
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {Faq !== undefined
                ? Faq.filter(data => {
                    return data.title.indexOf(input) > -1;
                  }).map(user => (
                    <TableRow className={classes.tableRow} hover key={user.bno}>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1.1rem' }}>
                        {user.title}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1.1rem' }}>
                        {user.content}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1.1rem' }}>
                        {moment(user.regdate).format('DD/MM/YYYY')}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1.1rem' }}>
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
